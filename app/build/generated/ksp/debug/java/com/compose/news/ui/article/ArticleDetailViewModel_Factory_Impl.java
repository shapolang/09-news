package com.compose.news.ui.article;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ArticleDetailViewModel_Factory_Impl implements ArticleDetailViewModel.Factory {
  private final ArticleDetailViewModel_Factory delegateFactory;

  ArticleDetailViewModel_Factory_Impl(ArticleDetailViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public ArticleDetailViewModel create(long articleId) {
    return delegateFactory.get(articleId);
  }

  public static Provider<ArticleDetailViewModel.Factory> create(
      ArticleDetailViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new ArticleDetailViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<ArticleDetailViewModel.Factory> createFactoryProvider(
      ArticleDetailViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new ArticleDetailViewModel_Factory_Impl(delegateFactory));
  }
}
