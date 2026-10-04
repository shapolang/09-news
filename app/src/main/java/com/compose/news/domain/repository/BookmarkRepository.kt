package com.compose.news.domain.repository

import com.compose.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * 收藏仓库契约。收藏记录按 userId 隔离，游客不能写入。
 */
interface BookmarkRepository {
    fun observeBookmarkedIds(userId: Long): Flow<Set<Long>>

    fun observeBookmarks(userId: Long): Flow<List<Article>>

    suspend fun toggle(userId: Long, article: Article)

    suspend fun isBookmarked(userId: Long, articleId: Long): Boolean
}
