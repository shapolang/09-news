package com.compose.news.ui.auth;

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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<AuthUseCases> authUseCasesProvider;

  private AuthViewModel_Factory(Provider<AuthUseCases> authUseCasesProvider) {
    this.authUseCasesProvider = authUseCasesProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authUseCasesProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<AuthUseCases> authUseCasesProvider) {
    return new AuthViewModel_Factory(authUseCasesProvider);
  }

  public static AuthViewModel newInstance(AuthUseCases authUseCases) {
    return new AuthViewModel(authUseCases);
  }
}
