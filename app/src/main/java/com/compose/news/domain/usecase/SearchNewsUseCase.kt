package com.compose.news.domain.usecase

import com.compose.news.domain.repository.NewsRepository
import javax.inject.Inject

/** 按关键词搜索公开 API，同样走 Paging 3。 */
class SearchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(query: String) = newsRepository.pagedSearch(query)
}
