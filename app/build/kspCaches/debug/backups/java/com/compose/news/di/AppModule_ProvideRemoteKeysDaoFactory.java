package com.compose.news.di;

import com.compose.news.data.local.dao.RemoteKeysDao;
import com.compose.news.data.local.db.NewsDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class AppModule_ProvideRemoteKeysDaoFactory implements Factory<RemoteKeysDao> {
  private final Provider<NewsDatabase> dbProvider;

  private AppModule_ProvideRemoteKeysDaoFactory(Provider<NewsDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public RemoteKeysDao get() {
    return provideRemoteKeysDao(dbProvider.get());
  }

  public static AppModule_ProvideRemoteKeysDaoFactory create(Provider<NewsDatabase> dbProvider) {
    return new AppModule_ProvideRemoteKeysDaoFactory(dbProvider);
  }

  public static RemoteKeysDao provideRemoteKeysDao(NewsDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRemoteKeysDao(db));
  }
}
