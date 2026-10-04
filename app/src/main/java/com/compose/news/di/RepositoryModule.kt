package com.compose.news.di

import com.compose.news.data.repository.AuthRepositoryImpl
import com.compose.news.data.repository.BookmarkRepositoryImpl
import com.compose.news.data.repository.NewsRepositoryImpl
import com.compose.news.data.repository.UserPreferencesRepositoryImpl
import com.compose.news.domain.repository.AuthRepository
import com.compose.news.domain.repository.BookmarkRepository
import com.compose.news.domain.repository.NewsRepository
import com.compose.news.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 仓库接口 → 实现。使用 `@Binds` 而不是 `@Provides`，符合 Hilt 官方最佳实践：
 * 少一次实例化代码、编译期校验实现类已带 `@Inject` 构造。
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(impl: BookmarkRepositoryImpl): BookmarkRepository

    @Binds
    @Singleton
    abstract fun bindPreferences(impl: UserPreferencesRepositoryImpl): UserPreferencesRepository
}
