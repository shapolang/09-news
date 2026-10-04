package com.compose.news.domain.usecase;

import com.compose.news.domain.repository.NewsRepository;
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
public final class ObserveArticleUseCase_Factory implements Factory<ObserveArticleUseCase> {
  private final Provider<NewsRepository> newsRepositoryProvider;

  private ObserveArticleUseCase_Factory(Provider<NewsRepository> newsRepositoryProvider) {
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  @Override
  public ObserveArticleUseCase get() {
    return newInstance(newsRepositoryProvider.get());
  }

  public static ObserveArticleUseCase_Factory create(
      Provider<NewsRepository> newsRepositoryProvider) {
    return new ObserveArticleUseCase_Factory(newsRepositoryProvider);
  }

  public static ObserveArticleUseCase newInstance(NewsRepository newsRepository) {
    return new ObserveArticleUseCase(newsRepository);
  }
}
