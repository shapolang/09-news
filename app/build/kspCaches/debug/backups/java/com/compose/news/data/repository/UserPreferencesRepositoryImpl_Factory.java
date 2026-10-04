package com.compose.news.data.repository;

import com.compose.news.data.local.prefs.ThemeDataSource;
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
public final class UserPreferencesRepositoryImpl_Factory implements Factory<UserPreferencesRepositoryImpl> {
  private final Provider<ThemeDataSource> themeDataSourceProvider;

  private UserPreferencesRepositoryImpl_Factory(Provider<ThemeDataSource> themeDataSourceProvider) {
    this.themeDataSourceProvider = themeDataSourceProvider;
  }

  @Override
  public UserPreferencesRepositoryImpl get() {
    return newInstance(themeDataSourceProvider.get());
  }

  public static UserPreferencesRepositoryImpl_Factory create(
      Provider<ThemeDataSource> themeDataSourceProvider) {
    return new UserPreferencesRepositoryImpl_Factory(themeDataSourceProvider);
  }

  public static UserPreferencesRepositoryImpl newInstance(ThemeDataSource themeDataSource) {
    return new UserPreferencesRepositoryImpl(themeDataSource);
  }
}
