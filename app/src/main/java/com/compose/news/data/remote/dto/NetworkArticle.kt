package com.compose.news.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Spaceflight News API v4 分页信封。
 * 文档：https://api.spaceflightnewsapi.net/v4/docs/
 */
@Serializable
data class NetworkArticlePage(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<NetworkArticle> = emptyList(),
)

/**
 * 单篇新闻的网络 DTO。未知字段由 Json { ignoreUnknownKeys = true } 丢弃，
 * 避免 launches/events 等嵌套结构污染领域模型。
 */
@Serializable
data class NetworkArticle(
    val id: Long,
    val title: String,
    val url: String,
    @SerialName("image_url") val imageUrl: String = "",
    @SerialName("news_site") val newsSite: String = "",
    val summary: String = "",
    @SerialName("published_at") val publishedAt: String = "",
    val featured: Boolean = false,
)
