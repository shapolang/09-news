package com.compose.news.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.compose.news.data.local.entity.RemoteKeysEntity

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM remote_keys WHERE cacheKey = :cacheKey")
    suspend fun remoteKeys(cacheKey: String): RemoteKeysEntity?

    @Upsert
    suspend fun upsert(keys: RemoteKeysEntity)

    @Query("DELETE FROM remote_keys WHERE cacheKey = :cacheKey")
    suspend fun deleteByCacheKey(cacheKey: String)
}
