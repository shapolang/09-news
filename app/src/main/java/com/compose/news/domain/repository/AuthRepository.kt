package com.compose.news.domain.repository

import com.compose.news.core.common.Result
import com.compose.news.domain.model.UserSession
import kotlinx.coroutines.flow.Flow

/**
 * 认证仓库契约。会话通过 DataStore 持久化，进程被杀后仍能保持登录。
 */
interface AuthRepository {
    val session: Flow<UserSession?>

    suspend fun signIn(email: String, password: String): Result<UserSession>

    suspend fun signUp(displayName: String, email: String, password: String): Result<UserSession>

    suspend fun signOut()
}
