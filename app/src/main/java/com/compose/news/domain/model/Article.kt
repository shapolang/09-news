package com.compose.news.domain.model

/**
 * 领域层新闻实体（UI 与 UseCase 只认识这个类型，不认识 Retrofit DTO / Room Entity）。
 *
 * 这是 Google 指南中 Data → Domain 映射的目标模型：剥离网络字段命名、缓存列、
 * 分页 cacheKey 等基础设施细节。
 */
data class Article(
    val id: Long,
    val title: String,
    val url: String,
    val imageUrl: String,
    val newsSite: String,
    val summary: String,
    val publishedAt: String,
    val featured: Boolean,
)
