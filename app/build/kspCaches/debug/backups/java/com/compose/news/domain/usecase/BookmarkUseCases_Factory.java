package com.compose.news.domain.usecase;

import com.compose.news.domain.repository.BookmarkRepository;
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
public final class BookmarkUseCases_Factory implements Factory<BookmarkUseCases> {
  private final Provider<BookmarkRepository> bookmarkRepositoryProvider;

  private BookmarkUseCases_Factory(Provider<BookmarkRepository> bookmarkRepositoryProvider) {
    this.bookmarkRepositoryProvider = bookmarkRepositoryProvider;
  }

  @Override
  public BookmarkUseCases get() {
    return newInstance(bookmarkRepositoryProvider.get());
  }

  public static BookmarkUseCases_Factory create(
      Provider<BookmarkRepository> bookmarkRepositoryProvider) {
    return new BookmarkUseCases_Factory(bookmarkRepositoryProvider);
  }

  public static BookmarkUseCases newInstance(BookmarkRepository bookmarkRepository) {
    return new BookmarkUseCases(bookmarkRepository);
  }
}
