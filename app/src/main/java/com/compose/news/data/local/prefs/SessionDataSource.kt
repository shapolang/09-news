package com.compose.news.data.local.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 登录会话。只存 userId，用户资料仍以 Room 为准，避免两处状态漂移。
 */
@Singleton
class SessionDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    val userId: Flow<Long?> = dataStore.data.map { prefs ->
        prefs[KEY_USER_ID]?.takeIf { it > 0L }
    }

    suspend fun setUserId(id: Long?) {
        dataStore.edit { prefs ->
            if (id == null) {
                prefs.remove(KEY_USER_ID)
            } else {
                prefs[KEY_USER_ID] = id
            }
        }
    }

    private companion object {
        val KEY_USER_ID = longPreferencesKey("session_user_id")
    }
}
