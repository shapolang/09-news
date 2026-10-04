package com.compose.news.ui.search;

import com.compose.news.domain.usecase.AuthUseCases;
import com.compose.news.domain.usecase.BookmarkUseCases;
import com.compose.news.domain.usecase.SearchNewsUseCase;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<SearchNewsUseCase> searchNewsProvider;

  private final Provider<BookmarkUseCases> bookmarkUseCasesProvider;

  private final Provider<AuthUseCases> authUseCasesProvider;

  private SearchViewModel_Factory(Provider<SearchNewsUseCase> searchNewsProvider,
      Provider<BookmarkUseCases> bookmarkUseCasesProvider,
      Provider<AuthUseCases> authUseCasesProvider) {
    this.searchNewsProvider = searchNewsProvider;
    this.bookmarkUseCasesProvider = bookmarkUseCasesProvider;
    this.authUseCasesProvider = authUseCasesProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(searchNewsProvider.get(), bookmarkUseCasesProvider.get(), authUseCasesProvider.get());
  }

  public static SearchViewModel_Factory create(Provider<SearchNewsUseCase> searchNewsProvider,
      Provider<BookmarkUseCases> bookmarkUseCasesProvider,
      Provider<AuthUseCases> authUseCasesProvider) {
    return new SearchViewModel_Factory(searchNewsProvider, bookmarkUseCasesProvider, authUseCasesProvider);
  }

  public static SearchViewModel newInstance(SearchNewsUseCase searchNews,
      BookmarkUseCases bookmarkUseCases, AuthUseCases authUseCases) {
    return new SearchViewModel(searchNews, bookmarkUseCases, authUseCases);
  }
}
