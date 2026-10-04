package com.compose.news.domain.repository

import androidx.paging.PagingData
import com.compose.news.domain.model.Article
import com.compose.news.domain.model.NewsChannel
import kotlinx.coroutines.flow.Flow

/**
 * 新闻仓库契约（领域层）。
 *
 * UI / UseCase 只依赖接口，由 data 层实现。这保证：
 * - 可以替换 SNAPI 为其它 API 而不改 UI
 * - 单元测试可以用 Fake 实现
 *
 * 资讯流采用 Paging 3 + Room RemoteMediator：Room 是单一数据源，网络只负责刷新缓存。
 */
interface NewsRepository {
    fun pagedFeed(channel: NewsChannel): Flow<PagingData<Article>>

    fun pagedSearch(query: String): Flow<PagingData<Article>>

    fun observeArticle(id: Long): Flow<Article?>

    suspend fun refreshArticle(id: Long)
}
