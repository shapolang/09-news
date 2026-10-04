package com.compose.news.data.remote.api

import com.compose.news.data.remote.dto.NetworkArticle
import com.compose.news.data.remote.dto.NetworkArticlePage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Spaceflight News API v4 Retrofit 接口。
 *
 * 选择该 API 的原因：完全公开、无需 Key、支持 search / news_site / offset 分页，
 * 适合作为离线优先新闻客户端的真实后端。
 *
 * 基址：https://api.spaceflightnewsapi.net/
 */
interface SpaceflightNewsApi {
    @GET("v4/articles/")
    suspend fun getArticles(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("search") search: String? = null,
        @Query("news_site") newsSite: String? = null,
        @Query("ordering") ordering: String = "-published_at",
    ): NetworkArticlePage

    @GET("v4/articles/{id}/")
    suspend fun getArticle(@Path("id") id: Long): NetworkArticle
}
