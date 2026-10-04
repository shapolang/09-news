package com.compose.news.data.repository;

import com.compose.news.data.local.dao.BookmarkDao;
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
public final class BookmarkRepositoryImpl_Factory implements Factory<BookmarkRepositoryImpl> {
  private final Provider<BookmarkDao> bookmarkDaoProvider;

  private BookmarkRepositoryImpl_Factory(Provider<BookmarkDao> bookmarkDaoProvider) {
    this.bookmarkDaoProvider = bookmarkDaoProvider;
  }

  @Override
  public BookmarkRepositoryImpl get() {
    return newInstance(bookmarkDaoProvider.get());
  }

  public static BookmarkRepositoryImpl_Factory create(Provider<BookmarkDao> bookmarkDaoProvider) {
    return new BookmarkRepositoryImpl_Factory(bookmarkDaoProvider);
  }

  public static BookmarkRepositoryImpl newInstance(BookmarkDao bookmarkDao) {
    return new BookmarkRepositoryImpl(bookmarkDao);
  }
}
