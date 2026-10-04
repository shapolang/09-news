package com.compose.news.domain.usecase

import com.compose.news.domain.repository.NewsRepository
import javax.inject.Inject

class ObserveArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(id: Long) = newsRepository.observeArticle(id)
}
