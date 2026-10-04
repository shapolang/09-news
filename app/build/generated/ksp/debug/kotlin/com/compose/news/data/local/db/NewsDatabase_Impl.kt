package com.compose.news.`data`.local.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.compose.news.`data`.local.dao.ArticleDao
import com.compose.news.`data`.local.dao.ArticleDao_Impl
import com.compose.news.`data`.local.dao.BookmarkDao
import com.compose.news.`data`.local.dao.BookmarkDao_Impl
import com.compose.news.`data`.local.dao.RemoteKeysDao
import com.compose.news.`data`.local.dao.RemoteKeysDao_Impl
import com.compose.news.`data`.local.dao.UserDao
import com.compose.news.`data`.local.dao.UserDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class NewsDatabase_Impl : NewsDatabase() {
  private val _articleDao: Lazy<ArticleDao> = lazy {
    ArticleDao_Impl(this)
  }

  private val _remoteKeysDao: Lazy<RemoteKeysDao> = lazy {
    RemoteKeysDao_Impl(this)
  }

  private val _bookmarkDao: Lazy<BookmarkDao> = lazy {
    BookmarkDao_Impl(this)
  }

  private val _userDao: Lazy<UserDao> = lazy {
    UserDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "de40050dc6d6b542faafc92822cc6e74", "5841e49a69bd1bbabc58ded67c1ab368") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `articles` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `url` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `newsSite` TEXT NOT NULL, `summary` TEXT NOT NULL, `publishedAt` TEXT NOT NULL, `featured` INTEGER NOT NULL, `cacheKey` TEXT NOT NULL, PRIMARY KEY(`id`, `cacheKey`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_cacheKey` ON `articles` (`cacheKey`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_publishedAt` ON `articles` (`publishedAt`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_id` ON `articles` (`id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `remote_keys` (`cacheKey` TEXT NOT NULL, `nextOffset` INTEGER, PRIMARY KEY(`cacheKey`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `bookmarks` (`userId` INTEGER NOT NULL, `articleId` INTEGER NOT NULL, `title` TEXT NOT NULL, `url` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `newsSite` TEXT NOT NULL, `summary` TEXT NOT NULL, `publishedAt` TEXT NOT NULL, `featured` INTEGER NOT NULL, `savedAt` INTEGER NOT NULL, PRIMARY KEY(`userId`, `articleId`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_bookmarks_userId` ON `bookmarks` (`userId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_bookmarks_articleId` ON `bookmarks` (`articleId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `email` TEXT NOT NULL, `displayName` TEXT NOT NULL, `passwordHash` TEXT NOT NULL, `salt` TEXT NOT NULL, `createdAt` INTEGER NOT NULL)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_users_email` ON `users` (`email`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'de40050dc6d6b542faafc92822cc6e74')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `articles`")
        connection.execSQL("DROP TABLE IF EXISTS `remote_keys`")
        connection.execSQL("DROP TABLE IF EXISTS `bookmarks`")
        connection.execSQL("DROP TABLE IF EXISTS `users`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsArticles: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsArticles.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("url", TableInfo.Column("url", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("imageUrl", TableInfo.Column("imageUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("newsSite", TableInfo.Column("newsSite", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("summary", TableInfo.Column("summary", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("publishedAt", TableInfo.Column("publishedAt", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("featured", TableInfo.Column("featured", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("cacheKey", TableInfo.Column("cacheKey", "TEXT", true, 2, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysArticles: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesArticles: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesArticles.add(TableInfo.Index("index_articles_cacheKey", false, listOf("cacheKey"),
            listOf("ASC")))
        _indicesArticles.add(TableInfo.Index("index_articles_publishedAt", false,
            listOf("publishedAt"), listOf("ASC")))
        _indicesArticles.add(TableInfo.Index("index_articles_id", false, listOf("id"),
            listOf("ASC")))
        val _infoArticles: TableInfo = TableInfo("articles", _columnsArticles, _foreignKeysArticles,
            _indicesArticles)
        val _existingArticles: TableInfo = read(connection, "articles")
        if (!_infoArticles.equals(_existingArticles)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |articles(com.compose.news.data.local.entity.ArticleEntity).
              | Expected:
              |""".trimMargin() + _infoArticles + """
              |
              | Found:
              |""".trimMargin() + _existingArticles)
        }
        val _columnsRemoteKeys: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRemoteKeys.put("cacheKey", TableInfo.Column("cacheKey", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRemoteKeys.put("nextOffset", TableInfo.Column("nextOffset", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRemoteKeys: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRemoteKeys: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRemoteKeys: TableInfo = TableInfo("remote_keys", _columnsRemoteKeys,
            _foreignKeysRemoteKeys, _indicesRemoteKeys)
        val _existingRemoteKeys: TableInfo = read(connection, "remote_keys")
        if (!_infoRemoteKeys.equals(_existingRemoteKeys)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |remote_keys(com.compose.news.data.local.entity.RemoteKeysEntity).
              | Expected:
              |""".trimMargin() + _infoRemoteKeys + """
              |
              | Found:
              |""".trimMargin() + _existingRemoteKeys)
        }
        val _columnsBookmarks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBookmarks.put("userId", TableInfo.Column("userId", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("articleId", TableInfo.Column("articleId", "INTEGER", true, 2, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("url", TableInfo.Column("url", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("imageUrl", TableInfo.Column("imageUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("newsSite", TableInfo.Column("newsSite", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("summary", TableInfo.Column("summary", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("publishedAt", TableInfo.Column("publishedAt", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("featured", TableInfo.Column("featured", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("savedAt", TableInfo.Column("savedAt", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBookmarks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBookmarks: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesBookmarks.add(TableInfo.Index("index_bookmarks_userId", false, listOf("userId"),
            listOf("ASC")))
        _indicesBookmarks.add(TableInfo.Index("index_bookmarks_articleId", false,
            listOf("articleId"), listOf("ASC")))
        val _infoBookmarks: TableInfo = TableInfo("bookmarks", _columnsBookmarks,
            _foreignKeysBookmarks, _indicesBookmarks)
        val _existingBookmarks: TableInfo = read(connection, "bookmarks")
        if (!_infoBookmarks.equals(_existingBookmarks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |bookmarks(com.compose.news.data.local.entity.BookmarkEntity).
              | Expected:
              |""".trimMargin() + _infoBookmarks + """
              |
              | Found:
              |""".trimMargin() + _existingBookmarks)
        }
        val _columnsUsers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsers.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("email", TableInfo.Column("email", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("displayName", TableInfo.Column("displayName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("passwordHash", TableInfo.Column("passwordHash", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("salt", TableInfo.Column("salt", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsers: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesUsers.add(TableInfo.Index("index_users_email", true, listOf("email"),
            listOf("ASC")))
        val _infoUsers: TableInfo = TableInfo("users", _columnsUsers, _foreignKeysUsers,
            _indicesUsers)
        val _existingUsers: TableInfo = read(connection, "users")
        if (!_infoUsers.equals(_existingUsers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |users(com.compose.news.data.local.entity.UserEntity).
              | Expected:
              |""".trimMargin() + _infoUsers + """
              |
              | Found:
              |""".trimMargin() + _existingUsers)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "articles", "remote_keys",
        "bookmarks", "users")
  }

  public override fun clearAllTables() {
    super.performClear(false, "articles", "remote_keys", "bookmarks", "users")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ArticleDao::class, ArticleDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RemoteKeysDao::class, RemoteKeysDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(BookmarkDao::class, BookmarkDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(UserDao::class, UserDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun articleDao(): ArticleDao = _articleDao.value

  public override fun remoteKeysDao(): RemoteKeysDao = _remoteKeysDao.value

  public override fun bookmarkDao(): BookmarkDao = _bookmarkDao.value

  public override fun userDao(): UserDao = _userDao.value
}
