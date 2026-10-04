package com.compose.news.domain.usecase

import com.compose.news.core.common.Result
import com.compose.news.domain.model.UserSession
import com.compose.news.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCases @Inject constructor(
    private val authRepository: AuthRepository,
) {
    val session = authRepository.session

    suspend fun signIn(email: String, password: String): Result<UserSession> {
        return authRepository.signIn(email.trim(), password)
    }

    suspend fun signUp(name: String, email: String, password: String): Result<UserSession> {
        return authRepository.signUp(name.trim(), email.trim(), password)
    }

    suspend fun signOut() = authRepository.signOut()
}
