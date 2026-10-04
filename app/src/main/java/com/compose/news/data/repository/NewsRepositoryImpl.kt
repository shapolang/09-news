package com.compose.news.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.compose.news.data.local.db.NewsDatabase
import com.compose.news.data.local.entity.toDomain
import com.compose.news.data.mapper.cacheKeyForFeed
import com.compose.news.data.mapper.cacheKeyForSearch
import com.compose.news.data.mapper.toEntity
import com.compose.news.data.remote.api.SpaceflightNewsApi
import com.compose.news.data.remote.paging.NewsRemoteMediator
import com.compose.news.domain.model.Article
import com.compose.news.domain.model.NewsChannel
import com.compose.news.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 新闻仓库实现：Room 单一数据源 + RemoteMediator 同步网络。
 */
@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val api: SpaceflightNewsApi,
    private val database: NewsDatabase,
) : NewsRepository {

    override fun pagedFeed(channel: NewsChannel): Flow<PagingData<Article>> {
        return pager(
            cacheKey = cacheKeyForFeed(channel.newsSite),
            newsSite = channel.newsSite,
            search = null,
        )
    }

    override fun pagedSearch(query: String): Flow<PagingData<Article>> {
        val trimmed = query.trim()
        return pager(
            cacheKey = cacheKeyForSearch(trimmed),
            newsSite = null,
            search = trimmed,
        )
    }

    override fun observeArticle(id: Long): Flow<Article?> {
        return database.articleDao().observeById(id).map { it?.toDomain() }
    }

    override suspend fun refreshArticle(id: Long) {
        val network = api.getArticle(id)
        val existing = database.articleDao().getById(id)
        val cacheKey = existing?.cacheKey ?: cacheKeyForFeed(null)
        database.articleDao().upsert(network.toEntity(cacheKey))
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun pager(
        cacheKey: String,
        newsSite: String?,
        search: String?,
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 6,
                initialLoadSize = PAGE_SIZE,
            ),
            remoteMediator = NewsRemoteMediator(
                cacheKey = cacheKey,
                newsSite = newsSite,
                search = search,
                api = api,
                database = database,
            ),
            pagingSourceFactory = { database.articleDao().pagingSource(cacheKey) },
        ).flow.map { paging -> paging.map { it.toDomain() } }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}
