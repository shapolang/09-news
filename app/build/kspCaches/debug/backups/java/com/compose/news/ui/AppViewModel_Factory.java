package com.compose.news.ui;

import com.compose.news.domain.repository.UserPreferencesRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class AppViewModel_Factory implements Factory<AppViewModel> {
  private final Provider<UserPreferencesRepository> preferencesProvider;

  private AppViewModel_Factory(Provider<UserPreferencesRepository> preferencesProvider) {
    this.preferencesProvider = preferencesProvider;
  }

  @Override
  public AppViewModel get() {
    return newInstance(preferencesProvider.get());
  }

  public static AppViewModel_Factory create(
      Provider<UserPreferencesRepository> preferencesProvider) {
    return new AppViewModel_Factory(preferencesProvider);
  }

  public static AppViewModel newInstance(UserPreferencesRepository preferences) {
    return new AppViewModel(preferences);
  }
}
