package com.compose.news.core.common

import java.security.MessageDigest
import java.security.SecureRandom

/**
 * 本地登录用的加盐哈希工具。
 *
 * 公开新闻 API 不提供用户体系，因此用 SHA-256(salt + password) 保存口令摘要，
 * 支撑登录 / 注册 / 收藏绑定用户。上线应改为服务端认证 + Credential Manager。
 */
object PasswordHasher {
    private val random = SecureRandom()

    fun newSalt(): String {
        val bytes = ByteArray(16)
        random.nextBytes(bytes)
        return bytes.toHex()
    }

    fun hash(password: String, salt: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest((salt + password).toByteArray(Charsets.UTF_8)).toHex()
    }

    fun matches(password: String, salt: String, expectedHash: String): Boolean {
        return hash(password, salt) == expectedHash
    }

    private fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }
}
