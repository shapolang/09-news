package com.compose.news.data.local.entity

import androidx.room.Entity
import androidx.room.Index

/**
 * 用户收藏。复合主键保证同一用户不会重复收藏同一篇。
 * 冗余保存文章字段，以便收藏列表在缓存被 RemoteMediator 清掉后仍可展示。
 */
@Entity(
    tableName = "bookmarks",
    primaryKeys = ["userId", "articleId"],
    indices = [Index("userId"), Index("articleId")],
)
data class BookmarkEntity(
    val userId: Long,
    val articleId: Long,
    val title: String,
    val url: String,
    val imageUrl: String,
    val newsSite: String,
    val summary: String,
    val publishedAt: String,
    val featured: Boolean,
    val savedAt: Long,
)
