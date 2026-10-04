package com.compose.news.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.compose.news.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles WHERE cacheKey = :cacheKey ORDER BY publishedAt DESC")
    fun pagingSource(cacheKey: String): PagingSource<Int, ArticleEntity>

    @Query("SELECT * FROM articles WHERE id = :id LIMIT 1")
    fun observeById(id: Long): Flow<ArticleEntity?>

    @Query("SELECT * FROM articles WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ArticleEntity?

    @Upsert
    suspend fun upsertAll(articles: List<ArticleEntity>)

    @Upsert
    suspend fun upsert(article: ArticleEntity)

    @Query("DELETE FROM articles WHERE cacheKey = :cacheKey")
    suspend fun deleteByCacheKey(cacheKey: String)
}
