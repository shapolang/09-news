package com.compose.news.data.mapper

import com.compose.news.data.local.entity.ArticleEntity
import com.compose.news.data.local.entity.BookmarkEntity
import com.compose.news.data.remote.dto.NetworkArticle
import com.compose.news.domain.model.Article

/**
 * 网络 DTO / 缓存 Entity / 领域模型 之间的纯函数映射。
 * 保持无 Android 依赖，便于 JVM 单元测试。
 */
fun NetworkArticle.toEntity(cacheKey: String): ArticleEntity = ArticleEntity(
    id = id,
    title = title,
    url = url,
    imageUrl = imageUrl,
    newsSite = newsSite,
    summary = summary,
    publishedAt = publishedAt,
    featured = featured,
    cacheKey = cacheKey,
)

fun NetworkArticle.toDomain(): Article = Article(
    id = id,
    title = title,
    url = url,
    imageUrl = imageUrl,
    newsSite = newsSite,
    summary = summary,
    publishedAt = publishedAt,
    featured = featured,
)

fun Article.toBookmark(userId: Long, savedAt: Long): BookmarkEntity = BookmarkEntity(
    userId = userId,
    articleId = id,
    title = title,
    url = url,
    imageUrl = imageUrl,
    newsSite = newsSite,
    summary = summary,
    publishedAt = publishedAt,
    featured = featured,
    savedAt = savedAt,
)

fun BookmarkEntity.toDomain(): Article = Article(
    id = articleId,
    title = title,
    url = url,
    imageUrl = imageUrl,
    newsSite = newsSite,
    summary = summary,
    publishedAt = publishedAt,
    featured = featured,
)

fun cacheKeyForFeed(newsSite: String?): String = "feed:" + (newsSite ?: "all")

fun cacheKeyForSearch(query: String): String = "search:" + query.trim().lowercase()
