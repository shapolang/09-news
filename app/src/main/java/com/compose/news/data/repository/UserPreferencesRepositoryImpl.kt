package com.compose.news.data.repository

import com.compose.news.data.local.prefs.ThemeDataSource
import com.compose.news.domain.model.ThemeMode
import com.compose.news.domain.repository.UserPreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val themeDataSource: ThemeDataSource,
) : UserPreferencesRepository {
    override val themeMode: Flow<ThemeMode> = themeDataSource.themeMode

    override suspend fun setThemeMode(mode: ThemeMode) {
        themeDataSource.setThemeMode(mode)
    }
}
