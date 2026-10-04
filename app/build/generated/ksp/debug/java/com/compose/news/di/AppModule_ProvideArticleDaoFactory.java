package com.compose.news.di;

import com.compose.news.data.local.dao.ArticleDao;
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
public final class AppModule_ProvideArticleDaoFactory implements Factory<ArticleDao> {
  private final Provider<NewsDatabase> dbProvider;

  private AppModule_ProvideArticleDaoFactory(Provider<NewsDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ArticleDao get() {
    return provideArticleDao(dbProvider.get());
  }

  public static AppModule_ProvideArticleDaoFactory create(Provider<NewsDatabase> dbProvider) {
    return new AppModule_ProvideArticleDaoFactory(dbProvider);
  }

  public static ArticleDao provideArticleDao(NewsDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideArticleDao(db));
  }
}
