package com.compose.news.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.compose.news.`data`.local.entity.BookmarkEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class BookmarkDao_Impl(
  __db: RoomDatabase,
) : BookmarkDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBookmarkEntity: EntityInsertAdapter<BookmarkEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfBookmarkEntity = object : EntityInsertAdapter<BookmarkEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `bookmarks` (`userId`,`articleId`,`title`,`url`,`imageUrl`,`newsSite`,`summary`,`publishedAt`,`featured`,`savedAt`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BookmarkEntity) {
        statement.bindLong(1, entity.userId)
        statement.bindLong(2, entity.articleId)
        statement.bindText(3, entity.title)
        statement.bindText(4, entity.url)
        statement.bindText(5, entity.imageUrl)
        statement.bindText(6, entity.newsSite)
        statement.bindText(7, entity.summary)
        statement.bindText(8, entity.publishedAt)
        val _tmp: Int = if (entity.featured) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        statement.bindLong(10, entity.savedAt)
      }
    }
  }

  public override suspend fun insert(entity: BookmarkEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfBookmarkEntity.insert(_connection, entity)
  }

  public override fun observeForUser(userId: Long): Flow<List<BookmarkEntity>> {
    val _sql: String = "SELECT * FROM bookmarks WHERE userId = ? ORDER BY savedAt DESC"
    return createFlow(__db, false, arrayOf("bookmarks")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfArticleId: Int = getColumnIndexOrThrow(_stmt, "articleId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfNewsSite: Int = getColumnIndexOrThrow(_stmt, "newsSite")
        val _columnIndexOfSummary: Int = getColumnIndexOrThrow(_stmt, "summary")
        val _columnIndexOfPublishedAt: Int = getColumnIndexOrThrow(_stmt, "publishedAt")
        val _columnIndexOfFeatured: Int = getColumnIndexOrThrow(_stmt, "featured")
        val _columnIndexOfSavedAt: Int = getColumnIndexOrThrow(_stmt, "savedAt")
        val _result: MutableList<BookmarkEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookmarkEntity
          val _tmpUserId: Long
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId)
          val _tmpArticleId: Long
          _tmpArticleId = _stmt.getLong(_columnIndexOfArticleId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpImageUrl: String
          _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          val _tmpNewsSite: String
          _tmpNewsSite = _stmt.getText(_columnIndexOfNewsSite)
          val _tmpSummary: String
          _tmpSummary = _stmt.getText(_columnIndexOfSummary)
          val _tmpPublishedAt: String
          _tmpPublishedAt = _stmt.getText(_columnIndexOfPublishedAt)
          val _tmpFeatured: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfFeatured).toInt()
          _tmpFeatured = _tmp != 0
          val _tmpSavedAt: Long
          _tmpSavedAt = _stmt.getLong(_columnIndexOfSavedAt)
          _item =
              BookmarkEntity(_tmpUserId,_tmpArticleId,_tmpTitle,_tmpUrl,_tmpImageUrl,_tmpNewsSite,_tmpSummary,_tmpPublishedAt,_tmpFeatured,_tmpSavedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeIds(userId: Long): Flow<List<Long>> {
    val _sql: String = "SELECT articleId FROM bookmarks WHERE userId = ?"
    return createFlow(__db, false, arrayOf("bookmarks")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        val _result: MutableList<Long> = mutableListOf()
        while (_stmt.step()) {
          val _item: Long
          _item = _stmt.getLong(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun exists(userId: Long, articleId: Long): Boolean {
    val _sql: String = "SELECT EXISTS(SELECT 1 FROM bookmarks WHERE userId = ? AND articleId = ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, articleId)
        val _result: Boolean
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp != 0
        } else {
          _result = false
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(userId: Long, articleId: Long) {
    val _sql: String = "DELETE FROM bookmarks WHERE userId = ? AND articleId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, articleId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
