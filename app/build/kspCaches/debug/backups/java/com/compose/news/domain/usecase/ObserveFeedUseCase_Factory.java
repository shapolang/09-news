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
public final class ObserveFeedUseCase_Factory implements Factory<ObserveFeedUseCase> {
  private final Provider<NewsRepository> newsRepositoryProvider;

  private ObserveFeedUseCase_Factory(Provider<NewsRepository> newsRepositoryProvider) {
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  @Override
  public ObserveFeedUseCase get() {
    return newInstance(newsRepositoryProvider.get());
  }

  public static ObserveFeedUseCase_Factory create(Provider<NewsRepository> newsRepositoryProvider) {
    return new ObserveFeedUseCase_Factory(newsRepositoryProvider);
  }

  public static ObserveFeedUseCase newInstance(NewsRepository newsRepository) {
    return new ObserveFeedUseCase(newsRepository);
  }
}
