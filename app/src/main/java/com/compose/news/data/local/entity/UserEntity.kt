package com.compose.news.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * 本地用户表。passwordHash 为加盐 SHA-256，salt 按用户随机生成。
 */
@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)],
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val email: String,
    val displayName: String,
    val passwordHash: String,
    val salt: String,
    val createdAt: Long,
)
