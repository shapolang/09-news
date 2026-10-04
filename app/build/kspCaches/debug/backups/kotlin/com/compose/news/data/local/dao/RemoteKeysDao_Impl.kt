package com.compose.news.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.compose.news.`data`.local.entity.RemoteKeysEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RemoteKeysDao_Impl(
  __db: RoomDatabase,
) : RemoteKeysDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfRemoteKeysEntity: EntityUpsertAdapter<RemoteKeysEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfRemoteKeysEntity = EntityUpsertAdapter<RemoteKeysEntity>(object :
        EntityInsertAdapter<RemoteKeysEntity>() {
      protected override fun createQuery(): String =
          "INSERT INTO `remote_keys` (`cacheKey`,`nextOffset`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RemoteKeysEntity) {
        statement.bindText(1, entity.cacheKey)
        val _tmpNextOffset: Int? = entity.nextOffset
        if (_tmpNextOffset == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpNextOffset.toLong())
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<RemoteKeysEntity>() {
      protected override fun createQuery(): String =
          "UPDATE `remote_keys` SET `cacheKey` = ?,`nextOffset` = ? WHERE `cacheKey` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RemoteKeysEntity) {
        statement.bindText(1, entity.cacheKey)
        val _tmpNextOffset: Int? = entity.nextOffset
        if (_tmpNextOffset == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpNextOffset.toLong())
        }
        statement.bindText(3, entity.cacheKey)
      }
    })
  }

  public override suspend fun upsert(keys: RemoteKeysEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __upsertAdapterOfRemoteKeysEntity.upsert(_connection, keys)
  }

  public override suspend fun remoteKeys(cacheKey: String): RemoteKeysEntity? {
    val _sql: String = "SELECT * FROM remote_keys WHERE cacheKey = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, cacheKey)
        val _columnIndexOfCacheKey: Int = getColumnIndexOrThrow(_stmt, "cacheKey")
        val _columnIndexOfNextOffset: Int = getColumnIndexOrThrow(_stmt, "nextOffset")
        val _result: RemoteKeysEntity?
        if (_stmt.step()) {
          val _tmpCacheKey: String
          _tmpCacheKey = _stmt.getText(_columnIndexOfCacheKey)
          val _tmpNextOffset: Int?
          if (_stmt.isNull(_columnIndexOfNextOffset)) {
            _tmpNextOffset = null
          } else {
            _tmpNextOffset = _stmt.getLong(_columnIndexOfNextOffset).toInt()
          }
          _result = RemoteKeysEntity(_tmpCacheKey,_tmpNextOffset)
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
    val _sql: String = "DELETE FROM remote_keys WHERE cacheKey = ?"
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
