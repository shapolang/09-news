package com.compose.news.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.news.domain.model.Article
import com.compose.news.domain.model.UserSession
import com.compose.news.domain.repository.NewsRepository
import com.compose.news.domain.usecase.AuthUseCases
import com.compose.news.domain.usecase.BookmarkUseCases
import com.compose.news.domain.usecase.ObserveArticleUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel(assistedFactory = ArticleDetailViewModel.Factory::class)
class ArticleDetailViewModel @AssistedInject constructor(
    @Assisted val articleId: Long,
    observeArticle: ObserveArticleUseCase,
    private val newsRepository: NewsRepository,
    private val bookmarkUseCases: BookmarkUseCases,
    authUseCases: AuthUseCases,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(articleId: Long): ArticleDetailViewModel
    }

    val article: StateFlow<Article?> = observeArticle(articleId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    val session: StateFlow<UserSession?> = authUseCases.session.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null,
    )

    val bookmarked: StateFlow<Boolean> = session
        .flatMapLatest { user ->
            if (user == null) flowOf(false)
            else bookmarkUseCases.ids(user.userId).flatMapLatest { ids -> flowOf(articleId in ids) }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    init {
        viewModelScope.launch {
            runCatching { newsRepository.refreshArticle(articleId) }
        }
    }

    fun toggleBookmark(onNeedLogin: () -> Unit) {
        val user = session.value
        val current = article.value
        if (user == null) {
            onNeedLogin()
            return
        }
        if (current != null) {
            viewModelScope.launch { bookmarkUseCases.toggle(user.userId, current) }
        }
    }
}
