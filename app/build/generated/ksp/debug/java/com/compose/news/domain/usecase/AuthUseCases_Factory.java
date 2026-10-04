package com.compose.news.domain.usecase;

import com.compose.news.domain.repository.AuthRepository;
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
public final class AuthUseCases_Factory implements Factory<AuthUseCases> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private AuthUseCases_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public AuthUseCases get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static AuthUseCases_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new AuthUseCases_Factory(authRepositoryProvider);
  }

  public static AuthUseCases newInstance(AuthRepository authRepository) {
    return new AuthUseCases(authRepository);
  }
}
