package com.compose.news.data.local.entity

import androidx.room.Entity

/**
 * Paging 3 RemoteMediator 使用的下一页 offset。
 * 每个 cacheKey（频道或搜索词）独立维护。
 */
@Entity(tableName = "remote_keys", primaryKeys = ["cacheKey"])
data class RemoteKeysEntity(
    val cacheKey: String,
    val nextOffset: Int?,
)
