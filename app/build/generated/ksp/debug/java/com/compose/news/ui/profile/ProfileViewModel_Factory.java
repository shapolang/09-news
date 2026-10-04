package com.compose.news.ui.profile;

import com.compose.news.domain.repository.UserPreferencesRepository;
import com.compose.news.domain.usecase.AuthUseCases;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<AuthUseCases> authUseCasesProvider;

  private final Provider<UserPreferencesRepository> preferencesProvider;

  private ProfileViewModel_Factory(Provider<AuthUseCases> authUseCasesProvider,
      Provider<UserPreferencesRepository> preferencesProvider) {
    this.authUseCasesProvider = authUseCasesProvider;
    this.preferencesProvider = preferencesProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(authUseCasesProvider.get(), preferencesProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<AuthUseCases> authUseCasesProvider,
      Provider<UserPreferencesRepository> preferencesProvider) {
    return new ProfileViewModel_Factory(authUseCasesProvider, preferencesProvider);
  }

  public static ProfileViewModel newInstance(AuthUseCases authUseCases,
      UserPreferencesRepository preferences) {
    return new ProfileViewModel(authUseCases, preferences);
  }
}
