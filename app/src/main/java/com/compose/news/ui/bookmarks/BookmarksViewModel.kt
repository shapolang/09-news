package com.compose.news.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.news.domain.model.Article
import com.compose.news.domain.model.UserSession
import com.compose.news.domain.usecase.AuthUseCases
import com.compose.news.domain.usecase.BookmarkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val bookmarkUseCases: BookmarkUseCases,
    authUseCases: AuthUseCases,
) : ViewModel() {

    val session: StateFlow<UserSession?> = authUseCases.session.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null,
    )

    val articles: StateFlow<List<Article>> = session
        .flatMapLatest { user ->
            if (user == null) flowOf(emptyList()) else bookmarkUseCases.articles(user.userId)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun remove(article: Article) {
        val user = session.value ?: return
        viewModelScope.launch { bookmarkUseCases.toggle(user.userId, article) }
    }
}
