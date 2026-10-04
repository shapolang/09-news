package com.compose.news.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.compose.news.`data`.local.entity.UserEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUserEntity: EntityInsertAdapter<UserEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfUserEntity = object : EntityInsertAdapter<UserEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `users` (`id`,`email`,`displayName`,`passwordHash`,`salt`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: UserEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.email)
        statement.bindText(3, entity.displayName)
        statement.bindText(4, entity.passwordHash)
        statement.bindText(5, entity.salt)
        statement.bindLong(6, entity.createdAt)
      }
    }
  }

  public override suspend fun insert(user: UserEntity): Long = performSuspending(__db, false, true)
      { _connection ->
    val _result: Long = __insertAdapterOfUserEntity.insertAndReturnId(_connection, user)
    _result
  }

  public override suspend fun findByEmail(email: String): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE email = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfPasswordHash: Int = getColumnIndexOrThrow(_stmt, "passwordHash")
        val _columnIndexOfSalt: Int = getColumnIndexOrThrow(_stmt, "salt")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpPasswordHash: String
          _tmpPasswordHash = _stmt.getText(_columnIndexOfPasswordHash)
          val _tmpSalt: String
          _tmpSalt = _stmt.getText(_columnIndexOfSalt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result =
              UserEntity(_tmpId,_tmpEmail,_tmpDisplayName,_tmpPasswordHash,_tmpSalt,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun findById(id: Long): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "displayName")
        val _columnIndexOfPasswordHash: Int = getColumnIndexOrThrow(_stmt, "passwordHash")
        val _columnIndexOfSalt: Int = getColumnIndexOrThrow(_stmt, "salt")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpPasswordHash: String
          _tmpPasswordHash = _stmt.getText(_columnIndexOfPasswordHash)
          val _tmpSalt: String
          _tmpSalt = _stmt.getText(_columnIndexOfSalt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result =
              UserEntity(_tmpId,_tmpEmail,_tmpDisplayName,_tmpPasswordHash,_tmpSalt,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
