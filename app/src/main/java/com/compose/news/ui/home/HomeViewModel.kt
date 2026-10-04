package com.compose.news.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.compose.news.data.remote.NetworkMonitor
import com.compose.news.domain.model.Article
import com.compose.news.domain.model.NewsChannel
import com.compose.news.domain.model.UserSession
import com.compose.news.domain.usecase.AuthUseCases
import com.compose.news.domain.usecase.BookmarkUseCases
import com.compose.news.domain.usecase.ObserveFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * 首页 ViewModel。
 *
 * PagingData 以 Flow 暴露并 [cachedIn] 到 viewModelScope，这是 Paging 3 官方要求，
 * 不能再包一层 StateFlow，否则 LazyPagingItems 无法正确订阅分页事件。
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    observeFeed: ObserveFeedUseCase,
    private val bookmarkUseCases: BookmarkUseCases,
    authUseCases: AuthUseCases,
    networkMonitor: NetworkMonitor,
) : ViewModel() {

    private val _channel = MutableStateFlow(NewsChannel.ALL)
    val channel: StateFlow<NewsChannel> = _channel

    val feed: Flow<PagingData<Article>> = _channel
        .flatMapLatest { observeFeed(it) }
        .cachedIn(viewModelScope)

    val session: StateFlow<UserSession?> = authUseCases.session.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null,
    )

    val bookmarkIds: StateFlow<Set<Long>> = session
        .flatMapLatest { user ->
            if (user == null) flowOf(emptySet())
            else bookmarkUseCases.ids(user.userId)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptySet())

    val isOnline: StateFlow<Boolean> = networkMonitor.isOnline.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        true,
    )

    fun onChannelSelected(channel: NewsChannel) {
        _channel.value = channel
    }

    fun onToggleBookmark(article: Article, onNeedLogin: () -> Unit) {
        val user = session.value
        if (user == null) {
            onNeedLogin()
            return
        }
        viewModelScope.launch { bookmarkUseCases.toggle(user.userId, article) }
    }
}
