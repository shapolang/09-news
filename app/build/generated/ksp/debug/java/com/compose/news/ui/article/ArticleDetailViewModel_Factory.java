package com.compose.news.ui.article;

import com.compose.news.domain.repository.NewsRepository;
import com.compose.news.domain.usecase.AuthUseCases;
import com.compose.news.domain.usecase.BookmarkUseCases;
import com.compose.news.domain.usecase.ObserveArticleUseCase;
import dagger.internal.DaggerGenerated;
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
public final class ArticleDetailViewModel_Factory {
  private final Provider<ObserveArticleUseCase> observeArticleProvider;

  private final Provider<NewsRepository> newsRepositoryProvider;

  private final Provider<BookmarkUseCases> bookmarkUseCasesProvider;

  private final Provider<AuthUseCases> authUseCasesProvider;

  private ArticleDetailViewModel_Factory(Provider<ObserveArticleUseCase> observeArticleProvider,
      Provider<NewsRepository> newsRepositoryProvider,
      Provider<BookmarkUseCases> bookmarkUseCasesProvider,
      Provider<AuthUseCases> authUseCasesProvider) {
    this.observeArticleProvider = observeArticleProvider;
    this.newsRepositoryProvider = newsRepositoryProvider;
    this.bookmarkUseCasesProvider = bookmarkUseCasesProvider;
    this.authUseCasesProvider = authUseCasesProvider;
  }

  public ArticleDetailViewModel get(long articleId) {
    return newInstance(articleId, observeArticleProvider.get(), newsRepositoryProvider.get(), bookmarkUseCasesProvider.get(), authUseCasesProvider.get());
  }

  public static ArticleDetailViewModel_Factory create(
      Provider<ObserveArticleUseCase> observeArticleProvider,
      Provider<NewsRepository> newsRepositoryProvider,
      Provider<BookmarkUseCases> bookmarkUseCasesProvider,
      Provider<AuthUseCases> authUseCasesProvider) {
    return new ArticleDetailViewModel_Factory(observeArticleProvider, newsRepositoryProvider, bookmarkUseCasesProvider, authUseCasesProvider);
  }

  public static ArticleDetailViewModel newInstance(long articleId,
      ObserveArticleUseCase observeArticle, NewsRepository newsRepository,
      BookmarkUseCases bookmarkUseCases, AuthUseCases authUseCases) {
    return new ArticleDetailViewModel(articleId, observeArticle, newsRepository, bookmarkUseCases, authUseCases);
  }
}
