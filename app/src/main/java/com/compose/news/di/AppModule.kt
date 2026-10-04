package com.compose.news.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.compose.news.data.local.db.NewsDatabase
import com.compose.news.data.remote.api.SpaceflightNewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

/**
 * 基础设施绑定：OkHttp / Retrofit / Room / DataStore。
 * 全部 `@Singleton`，与 Google 指南「单一数据源、单一客户端」一致。
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(okHttp: OkHttpClient, json: Json): SpaceflightNewsApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(SpaceflightNewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(context, NewsDatabase::class.java, "horizon-news.db")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    @Provides
    fun provideArticleDao(db: NewsDatabase) = db.articleDao()

    @Provides
    fun provideBookmarkDao(db: NewsDatabase) = db.bookmarkDao()

    @Provides
    fun provideUserDao(db: NewsDatabase) = db.userDao()

    @Provides
    fun provideRemoteKeysDao(db: NewsDatabase) = db.remoteKeysDao()

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("horizon_prefs") },
        )
    }

    private const val BASE_URL = "https://api.spaceflightnewsapi.net/"
}
