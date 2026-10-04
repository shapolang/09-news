package com.compose.news.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.news.domain.model.ThemeMode
import com.compose.news.domain.model.UserSession
import com.compose.news.domain.repository.UserPreferencesRepository
import com.compose.news.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val preferences: UserPreferencesRepository,
) : ViewModel() {

    val session: StateFlow<UserSession?> = authUseCases.session.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null,
    )

    val themeMode: StateFlow<ThemeMode> = preferences.themeMode.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ThemeMode.SYSTEM,
    )

    fun setTheme(mode: ThemeMode) {
        viewModelScope.launch { preferences.setThemeMode(mode) }
    }

    fun signOut() {
        viewModelScope.launch { authUseCases.signOut() }
    }
}
