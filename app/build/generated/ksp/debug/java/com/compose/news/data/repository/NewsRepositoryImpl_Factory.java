package com.compose.news.data.repository;

import com.compose.news.data.local.db.NewsDatabase;
import com.compose.news.data.remote.api.SpaceflightNewsApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class NewsRepositoryImpl_Factory implements Factory<NewsRepositoryImpl> {
  private final Provider<SpaceflightNewsApi> apiProvider;

  private final Provider<NewsDatabase> databaseProvider;

  private NewsRepositoryImpl_Factory(Provider<SpaceflightNewsApi> apiProvider,
      Provider<NewsDatabase> databaseProvider) {
    this.apiProvider = apiProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public NewsRepositoryImpl get() {
    return newInstance(apiProvider.get(), databaseProvider.get());
  }

  public static NewsRepositoryImpl_Factory create(Provider<SpaceflightNewsApi> apiProvider,
      Provider<NewsDatabase> databaseProvider) {
    return new NewsRepositoryImpl_Factory(apiProvider, databaseProvider);
  }

  public static NewsRepositoryImpl newInstance(SpaceflightNewsApi api, NewsDatabase database) {
    return new NewsRepositoryImpl(api, database);
  }
}
