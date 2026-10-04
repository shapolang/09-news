package com.compose.news.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.news.data.local.dao.ArticleDao
import com.compose.news.data.local.dao.BookmarkDao
import com.compose.news.data.local.dao.RemoteKeysDao
import com.compose.news.data.local.dao.UserDao
import com.compose.news.data.local.entity.ArticleEntity
import com.compose.news.data.local.entity.BookmarkEntity
import com.compose.news.data.local.entity.RemoteKeysEntity
import com.compose.news.data.local.entity.UserEntity

/**
 * 应用唯一 Room 数据库。
 *
 * 表职责：
 * - articles：资讯离线缓存（Paging 单一数据源）
 * - remote_keys：分页游标
 * - bookmarks：按用户隔离的收藏快照
 * - users：本地账号
 */
@Database(
    entities = [
        ArticleEntity::class,
        RemoteKeysEntity::class,
        BookmarkEntity::class,
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun userDao(): UserDao
}
