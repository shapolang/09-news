package com.compose.news.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import com.compose.news.domain.model.Article

/**
 * 资讯缓存表。`cacheKey` 区分不同频道/搜索，Paging RemoteMediator 按 key 清理与追加。
 */
@Entity(
    tableName = "articles",
    primaryKeys = ["id", "cacheKey"],
    indices = [Index("cacheKey"), Index("publishedAt"), Index("id")],
)
data class ArticleEntity(
    val id: Long,
    val title: String,
    val url: String,
    val imageUrl: String,
    val newsSite: String,
    val summary: String,
    val publishedAt: String,
    val featured: Boolean,
    val cacheKey: String,
)

fun ArticleEntity.toDomain(): Article = Article(
    id = id,
    title = title,
    url = url,
    imageUrl = imageUrl,
    newsSite = newsSite,
    summary = summary,
    publishedAt = publishedAt,
    featured = featured,
)
