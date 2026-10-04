package com.compose.news.di;

import com.compose.news.data.local.dao.BookmarkDao;
import com.compose.news.data.local.db.NewsDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideBookmarkDaoFactory implements Factory<BookmarkDao> {
  private final Provider<NewsDatabase> dbProvider;

  private AppModule_ProvideBookmarkDaoFactory(Provider<NewsDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public BookmarkDao get() {
    return provideBookmarkDao(dbProvider.get());
  }

  public static AppModule_ProvideBookmarkDaoFactory create(Provider<NewsDatabase> dbProvider) {
    return new AppModule_ProvideBookmarkDaoFactory(dbProvider);
  }

  public static BookmarkDao provideBookmarkDao(NewsDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideBookmarkDao(db));
  }
}
