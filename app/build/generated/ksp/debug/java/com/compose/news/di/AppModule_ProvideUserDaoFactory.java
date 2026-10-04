package com.compose.news.di;

import com.compose.news.data.local.dao.UserDao;
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
public final class AppModule_ProvideUserDaoFactory implements Factory<UserDao> {
  private final Provider<NewsDatabase> dbProvider;

  private AppModule_ProvideUserDaoFactory(Provider<NewsDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public UserDao get() {
    return provideUserDao(dbProvider.get());
  }

  public static AppModule_ProvideUserDaoFactory create(Provider<NewsDatabase> dbProvider) {
    return new AppModule_ProvideUserDaoFactory(dbProvider);
  }

  public static UserDao provideUserDao(NewsDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUserDao(db));
  }
}
