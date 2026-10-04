package com.compose.news.data.remote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.compose.news.data.local.db.NewsDatabase
import com.compose.news.data.local.entity.ArticleEntity
import com.compose.news.data.local.entity.RemoteKeysEntity
import com.compose.news.data.mapper.toEntity
import com.compose.news.data.remote.api.SpaceflightNewsApi

/**
 * Google 推荐的「网络 + 数据库」分页胶水。
 *
 * 流程：
 * 1. Paging 需要数据时调用 [load]
 * 2. 按 LoadType 计算 offset，请求 SNAPI
 * 3. 在事务里写入 articles + remote_keys
 * 4. UI 只观察 Room 的 PagingSource，因此断网时仍能滚动已缓存页
 */
@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val cacheKey: String,
    private val newsSite: String?,
    private val search: String?,
    private val api: SpaceflightNewsApi,
    private val database: NewsDatabase,
) : RemoteMediator<Int, ArticleEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>,
    ): MediatorResult {
        val keysDao = database.remoteKeysDao()
        val articleDao = database.articleDao()
        val offset = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val keys = keysDao.remoteKeys(cacheKey)
                keys?.nextOffset ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }
        return try {
            val pageSize = state.config.pageSize
            val page = api.getArticles(
                limit = pageSize,
                offset = offset,
                search = search?.takeIf { it.isNotBlank() },
                newsSite = newsSite,
            )
            val end = page.next.isNullOrBlank() || page.results.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.deleteByCacheKey(cacheKey)
                    keysDao.deleteByCacheKey(cacheKey)
                }
                articleDao.upsertAll(page.results.map { it.toEntity(cacheKey) })
                keysDao.upsert(
                    RemoteKeysEntity(
                        cacheKey = cacheKey,
                        nextOffset = if (end) null else offset + page.results.size,
                    ),
                )
            }
            MediatorResult.Success(endOfPaginationReached = end)
        } catch (t: Throwable) {
            MediatorResult.Error(t)
        }
    }
}
