package com.compose.news.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.news.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks WHERE userId = :userId ORDER BY savedAt DESC")
    fun observeForUser(userId: Long): Flow<List<BookmarkEntity>>

    @Query("SELECT articleId FROM bookmarks WHERE userId = :userId")
    fun observeIds(userId: Long): Flow<List<Long>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE userId = :userId AND articleId = :articleId)")
    suspend fun exists(userId: Long, articleId: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE userId = :userId AND articleId = :articleId")
    suspend fun delete(userId: Long, articleId: Long)
}
