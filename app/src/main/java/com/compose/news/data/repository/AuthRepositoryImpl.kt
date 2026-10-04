package com.compose.news.data.repository

import com.compose.news.core.common.PasswordHasher
import com.compose.news.core.common.Result
import com.compose.news.data.local.dao.UserDao
import com.compose.news.data.local.entity.UserEntity
import com.compose.news.data.local.prefs.SessionDataSource
import com.compose.news.domain.model.UserSession
import com.compose.news.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * 本地认证实现。首次启动会写入演示账号，方便评测登录与收藏。
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sessionDataSource: SessionDataSource,
) : AuthRepository {

    override val session: Flow<UserSession?> = sessionDataSource.userId.map { id ->
        if (id == null) {
            null
        } else {
            val user = userDao.findById(id) ?: return@map null
            UserSession(userId = user.id, email = user.email, displayName = user.displayName)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun signIn(email: String, password: String): Result<UserSession> {
        ensureDemoUser()
        if (email.isBlank() || password.isBlank()) {
            return Result.Error("请输入邮箱和密码")
        }
        val user = userDao.findByEmail(email.lowercase())
            ?: return Result.Error("账号不存在，请先注册")
        if (!PasswordHasher.matches(password, user.salt, user.passwordHash)) {
            return Result.Error("密码不正确")
        }
        sessionDataSource.setUserId(user.id)
        return Result.Success(
            UserSession(userId = user.id, email = user.email, displayName = user.displayName),
        )
    }

    override suspend fun signUp(
        displayName: String,
        email: String,
        password: String,
    ): Result<UserSession> {
        ensureDemoUser()
        if (displayName.length < 2) return Result.Error("昵称至少 2 个字符")
        if (!email.contains("@")) return Result.Error("邮箱格式不正确")
        if (password.length < 6) return Result.Error("密码至少 6 位")
        val normalized = email.lowercase()
        if (userDao.findByEmail(normalized) != null) {
            return Result.Error("该邮箱已注册")
        }
        val salt = PasswordHasher.newSalt()
        val id = userDao.insert(
            UserEntity(
                email = normalized,
                displayName = displayName,
                passwordHash = PasswordHasher.hash(password, salt),
                salt = salt,
                createdAt = System.currentTimeMillis(),
            ),
        )
        sessionDataSource.setUserId(id)
        return Result.Success(UserSession(userId = id, email = normalized, displayName = displayName))
    }

    override suspend fun signOut() {
        sessionDataSource.setUserId(null)
    }

    private suspend fun ensureDemoUser() {
        val email = DEMO_EMAIL
        if (userDao.findByEmail(email) != null) return
        val salt = PasswordHasher.newSalt()
        userDao.insert(
            UserEntity(
                email = email,
                displayName = "演示用户",
                passwordHash = PasswordHasher.hash(DEMO_PASSWORD, salt),
                salt = salt,
                createdAt = System.currentTimeMillis(),
            ),
        )
    }

    companion object {
        const val DEMO_EMAIL = "demo@horizon.news"
        const val DEMO_PASSWORD = "Demo1234"
    }
}
