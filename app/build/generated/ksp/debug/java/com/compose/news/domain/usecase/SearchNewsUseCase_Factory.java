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
public final class SearchNewsUseCase_Factory implements Factory<SearchNewsUseCase> {
  private final Provider<NewsRepository> newsRepositoryProvider;

  private SearchNewsUseCase_Factory(Provider<NewsRepository> newsRepositoryProvider) {
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  @Override
  public SearchNewsUseCase get() {
    return newInstance(newsRepositoryProvider.get());
  }

  public static SearchNewsUseCase_Factory create(Provider<NewsRepository> newsRepositoryProvider) {
    return new SearchNewsUseCase_Factory(newsRepositoryProvider);
  }

  public static SearchNewsUseCase newInstance(NewsRepository newsRepository) {
    return new SearchNewsUseCase(newsRepository);
  }
}
