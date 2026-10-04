package com.compose.news.ui.home;

import com.compose.news.data.remote.NetworkMonitor;
import com.compose.news.domain.usecase.AuthUseCases;
import com.compose.news.domain.usecase.BookmarkUseCases;
import com.compose.news.domain.usecase.ObserveFeedUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<ObserveFeedUseCase> observeFeedProvider;

  private final Provider<BookmarkUseCases> bookmarkUseCasesProvider;

  private final Provider<AuthUseCases> authUseCasesProvider;

  private final Provider<NetworkMonitor> networkMonitorProvider;

  private HomeViewModel_Factory(Provider<ObserveFeedUseCase> observeFeedProvider,
      Provider<BookmarkUseCases> bookmarkUseCasesProvider,
      Provider<AuthUseCases> authUseCasesProvider,
      Provider<NetworkMonitor> networkMonitorProvider) {
    this.observeFeedProvider = observeFeedProvider;
    this.bookmarkUseCasesProvider = bookmarkUseCasesProvider;
    this.authUseCasesProvider = authUseCasesProvider;
    this.networkMonitorProvider = networkMonitorProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(observeFeedProvider.get(), bookmarkUseCasesProvider.get(), authUseCasesProvider.get(), networkMonitorProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<ObserveFeedUseCase> observeFeedProvider,
      Provider<BookmarkUseCases> bookmarkUseCasesProvider,
      Provider<AuthUseCases> authUseCasesProvider,
      Provider<NetworkMonitor> networkMonitorProvider) {
    return new HomeViewModel_Factory(observeFeedProvider, bookmarkUseCasesProvider, authUseCasesProvider, networkMonitorProvider);
  }

  public static HomeViewModel newInstance(ObserveFeedUseCase observeFeed,
      BookmarkUseCases bookmarkUseCases, AuthUseCases authUseCases, NetworkMonitor networkMonitor) {
    return new HomeViewModel(observeFeed, bookmarkUseCases, authUseCases, networkMonitor);
  }
}
