package com.compose.news.domain.repository

import com.compose.news.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow

/**
 * 用户偏好（主题等）契约。实现落在 Jetpack DataStore，而不是 SharedPreferences。
 */
interface UserPreferencesRepository {
    val themeMode: Flow<ThemeMode>

    suspend fun setThemeMode(mode: ThemeMode)
}
