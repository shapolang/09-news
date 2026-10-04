package com.compose.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.news.domain.model.ThemeMode
import com.compose.news.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/** 根级 ViewModel：只负责主题，保证切换后整棵导航树一起重组。 */
@HiltViewModel
class AppViewModel @Inject constructor(
    preferences: UserPreferencesRepository,
) : ViewModel() {
    val themeMode: StateFlow<ThemeMode> = preferences.themeMode.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ThemeMode.SYSTEM,
    )
}
