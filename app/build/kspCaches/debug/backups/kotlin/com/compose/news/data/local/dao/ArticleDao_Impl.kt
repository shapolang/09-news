package com.compose.news.`data`.local.dao

import androidx.paging.PagingSource
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.coroutines.createFlow
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.compose.news.`data`.local.entity.ArticleEntity
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
public class ArticleDao_Impl(
  __db: RoomDatabase,
) : ArticleDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfArticleEntity: EntityUpsertAdapter<ArticleEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfArticleEntity = EntityUpsertAdapter<ArticleEntity>(object :
        EntityInsertAdapter<ArticleEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `articles` (`id`,`title`,`url`,`imageUrl`,`newsSite`,`summary`,`publishedAt`,`featured`,`cacheKey`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ArticleEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.url)
        statement.bindText(4, entity.imageUrl)
        statement.bindText(5, entity.newsSite)
        statement.bindText(6, entity.summary)
        statement.bindText(7, entity.publishedAt)
        val _tmp: Int = if (entity.featured) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindText(9, entity.cacheKey)
      }
    }, object : EntityDeleteOrUpdateAdapter<ArticleEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `articles` SET `id` = ?,`title` = ?,`url` = ?,`imageUrl` = ?,`newsSite` = ?,`summary` = ?,`publishedAt` = ?,`featured` = ?,`cacheKey` = ? WHERE `id` = ? AND `cacheKey` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ArticleEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.url)
        statement.bindText(4, entity.imageUrl)
        statement.bindText(5, entity.newsSite)
        statement.bindText(6, entity.summary)
        statement.bindText(7, entity.publishedAt)
        val _tmp: Int = if (entity.featured) 1 else 0
        statement.bindLong(8, _tmp.toLong())
        statement.bindText(9, entity.cacheKey)
        statement.bindLong(10, entity.id)
        statement.bindText(11, entity.cacheKey)
      }
    })
  }

  public override suspend fun upsertAll(articles: List<ArticleEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfArticleEntity.upsert(_connection, articles)
  }

  public override suspend fun upsert(article: ArticleEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __upsertAdapterOfArticleEntity.upsert(_connection, article)
  }

  public override fun pagingSource(cacheKey: String): PagingSource<Int, ArticleEntity> {
    val _sql: String = "SELECT * FROM articles WHERE cacheKey = ? ORDER BY publishedAt DESC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql) { _stmt ->
      var _argIndex: Int = 1
      _stmt.bindText(_argIndex, cacheKey)
    }
    return object : LimitOffsetPagingSource<ArticleEntity>(_rawQuery, __db, "articles") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int):
          List<ArticleEntity> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
          val _columnIndexOfNewsSite: Int = getColumnIndexOrThrow(_stmt, "newsSite")
          val _columnIndexOfSummary: Int = getColumnIndexOrThrow(_stmt, "summary")
          val _columnIndexOfPublishedAt: Int = getColumnIndexOrThrow(_stmt, "publishedAt")
          val _columnIndexOfFeatured: Int = getColumnIndexOrThrow(_stmt, "featured")
          val _columnIndexOfCacheKey: Int = getColumnIndexOrThrow(_stmt, "cacheKey")
          val _result: MutableList<ArticleEntity> = mutableListOf()
          while (_stmt.step()) {
            val _item: ArticleEntity
            val _tmpId: Long
            _tmpId = _stmt.getLong(_columnIndexOfId)
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
            val _tmpCacheKey: String
            _tmpCacheKey = _stmt.getText(_columnIndexOfCacheKey)
            _item =
                ArticleEntity(_tmpId,_tmpTitle,_tmpUrl,_tmpImageUrl,_tmpNewsSite,_tmpSummary,_tmpPublishedAt,_tmpFeatured,_tmpCacheKey)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun observeById(id: Long): Flow<ArticleEntity?> {
    val _sql: String = "SELECT * FROM articles WHERE id = ? LIMIT 1"
    return createFlow(__db, false, arrayOf("articles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfNewsSite: Int = getColumnIndexOrThrow(_stmt, "newsSite")
        val _columnIndexOfSummary: Int = getColumnIndexOrThrow(_stmt, "summary")
        val _columnIndexOfPublishedAt: Int = getColumnIndexOrThrow(_stmt, "publishedAt")
        val _columnIndexOfFeatured: Int = getColumnIndexOrThrow(_stmt, "featured")
        val _columnIndexOfCacheKey: Int = getColumnIndexOrThrow(_stmt, "cacheKey")
        val _result: ArticleEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpCacheKey: String
          _tmpCacheKey = _stmt.getText(_columnIndexOfCacheKey)
          _result =
              ArticleEntity(_tmpId,_tmpTitle,_tmpUrl,_tmpImageUrl,_tmpNewsSite,_tmpSummary,_tmpPublishedAt,_tmpFeatured,_tmpCacheKey)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): ArticleEntity? {
    val _sql: String = "SELECT * FROM articles WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfNewsSite: Int = getColumnIndexOrThrow(_stmt, "newsSite")
        val _columnIndexOfSummary: Int = getColumnIndexOrThrow(_stmt, "summary")
        val _columnIndexOfPublishedAt: Int = getColumnIndexOrThrow(_stmt, "publishedAt")
        val _columnIndexOfFeatured: Int = getColumnIndexOrThrow(_stmt, "featured")
        val _columnIndexOfCacheKey: Int = getColumnIndexOrThrow(_stmt, "cacheKey")
        val _result: ArticleEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpCacheKey: String
          _tmpCacheKey = _stmt.getText(_columnIndexOfCacheKey)
          _result =
              ArticleEntity(_tmpId,_tmpTitle,_tmpUrl,_tmpImageUrl,_tmpNewsSite,_tmpSummary,_tmpPublishedAt,_tmpFeatured,_tmpCacheKey)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByCacheKey(cacheKey: String) {
    val _sql: String = "DELETE FROM articles WHERE cacheKey = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, cacheKey)
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
