package com.compose.news.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.compose.news.domain.model.Article
import com.compose.news.domain.model.UserSession
import com.compose.news.domain.usecase.AuthUseCases
import com.compose.news.domain.usecase.BookmarkUseCases
import com.compose.news.domain.usecase.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    searchNews: SearchNewsUseCase,
    private val bookmarkUseCases: BookmarkUseCases,
    authUseCases: AuthUseCases,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    val results: Flow<PagingData<Article>> = _query
        .debounce(400)
        .distinctUntilChanged()
        .flatMapLatest { text ->
            if (text.trim().length < 2) flowOf(PagingData.empty())
            else searchNews(text)
        }
        .cachedIn(viewModelScope)

    val session: StateFlow<UserSession?> = authUseCases.session.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null,
    )

    val bookmarkIds: StateFlow<Set<Long>> = session
        .flatMapLatest { user ->
            if (user == null) flowOf(emptySet()) else bookmarkUseCases.ids(user.userId)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptySet())

    fun onQueryChange(value: String) {
        _query.value = value
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
