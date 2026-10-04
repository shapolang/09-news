package com.compose.news.domain.usecase

import com.compose.news.domain.model.NewsChannel
import com.compose.news.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * 观察某个频道的分页资讯流。
 *
 * UseCase 在 Google 指南中属于 Domain 层：当一个功能需要编排多个仓库、或需要被
 * 多个 ViewModel 复用时抽出。此处显式存在是为了让分层完整、调用意图可读。
 */
class ObserveFeedUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(channel: NewsChannel) = newsRepository.pagedFeed(channel)
}
