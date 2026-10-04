package com.compose.news.ui.bookmarks;

import com.compose.news.domain.usecase.AuthUseCases;
import com.compose.news.domain.usecase.BookmarkUseCases;
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
public final class BookmarksViewModel_Factory implements Factory<BookmarksViewModel> {
  private final Provider<BookmarkUseCases> bookmarkUseCasesProvider;

  private final Provider<AuthUseCases> authUseCasesProvider;

  private BookmarksViewModel_Factory(Provider<BookmarkUseCases> bookmarkUseCasesProvider,
      Provider<AuthUseCases> authUseCasesProvider) {
    this.bookmarkUseCasesProvider = bookmarkUseCasesProvider;
    this.authUseCasesProvider = authUseCasesProvider;
  }

  @Override
  public BookmarksViewModel get() {
    return newInstance(bookmarkUseCasesProvider.get(), authUseCasesProvider.get());
  }

  public static BookmarksViewModel_Factory create(
      Provider<BookmarkUseCases> bookmarkUseCasesProvider,
      Provider<AuthUseCases> authUseCasesProvider) {
    return new BookmarksViewModel_Factory(bookmarkUseCasesProvider, authUseCasesProvider);
  }

  public static BookmarksViewModel newInstance(BookmarkUseCases bookmarkUseCases,
      AuthUseCases authUseCases) {
    return new BookmarksViewModel(bookmarkUseCases, authUseCases);
  }
}
