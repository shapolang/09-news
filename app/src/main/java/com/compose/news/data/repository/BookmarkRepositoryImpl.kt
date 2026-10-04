package com.compose.news.data.repository

import com.compose.news.data.local.dao.BookmarkDao
import com.compose.news.data.mapper.toBookmark
import com.compose.news.data.mapper.toDomain
import com.compose.news.domain.model.Article
import com.compose.news.domain.repository.BookmarkRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao,
) : BookmarkRepository {
    override fun observeBookmarkedIds(userId: Long): Flow<Set<Long>> {
        return bookmarkDao.observeIds(userId).map { it.toSet() }
    }

    override fun observeBookmarks(userId: Long): Flow<List<Article>> {
        return bookmarkDao.observeForUser(userId).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun toggle(userId: Long, article: Article) {
        if (bookmarkDao.exists(userId, article.id)) {
            bookmarkDao.delete(userId, article.id)
        } else {
            bookmarkDao.insert(article.toBookmark(userId, System.currentTimeMillis()))
        }
    }

    override suspend fun isBookmarked(userId: Long, articleId: Long): Boolean {
        return bookmarkDao.exists(userId, articleId)
    }
}
