package com.compose.news.data.repository;

import com.compose.news.data.local.dao.UserDao;
import com.compose.news.data.local.prefs.SessionDataSource;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<UserDao> userDaoProvider;

  private final Provider<SessionDataSource> sessionDataSourceProvider;

  private AuthRepositoryImpl_Factory(Provider<UserDao> userDaoProvider,
      Provider<SessionDataSource> sessionDataSourceProvider) {
    this.userDaoProvider = userDaoProvider;
    this.sessionDataSourceProvider = sessionDataSourceProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(userDaoProvider.get(), sessionDataSourceProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(Provider<UserDao> userDaoProvider,
      Provider<SessionDataSource> sessionDataSourceProvider) {
    return new AuthRepositoryImpl_Factory(userDaoProvider, sessionDataSourceProvider);
  }

  public static AuthRepositoryImpl newInstance(UserDao userDao,
      SessionDataSource sessionDataSource) {
    return new AuthRepositoryImpl(userDao, sessionDataSource);
  }
}
