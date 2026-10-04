package com.compose.news.domain.model

/**
 * 当前登录会话。`null` 表示游客。
 * 收藏必须绑定 [userId]，避免多账号数据串台。
 */
data class UserSession(
    val userId: Long,
    val email: String,
    val displayName: String,
)
