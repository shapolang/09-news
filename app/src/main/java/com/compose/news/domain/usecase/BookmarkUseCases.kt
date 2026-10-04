package com.compose.news.domain.usecase

import com.compose.news.domain.model.Article
import com.compose.news.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkUseCases @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) {
    fun ids(userId: Long) = bookmarkRepository.observeBookmarkedIds(userId)

    fun articles(userId: Long) = bookmarkRepository.observeBookmarks(userId)

    suspend fun toggle(userId: Long, article: Article) = bookmarkRepository.toggle(userId, article)
}
