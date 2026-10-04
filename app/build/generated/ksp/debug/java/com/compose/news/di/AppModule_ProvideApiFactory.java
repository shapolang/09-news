package com.compose.news.di;

import com.compose.news.data.remote.api.SpaceflightNewsApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;

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
public final class AppModule_ProvideApiFactory implements Factory<SpaceflightNewsApi> {
  private final Provider<OkHttpClient> okHttpProvider;

  private final Provider<Json> jsonProvider;

  private AppModule_ProvideApiFactory(Provider<OkHttpClient> okHttpProvider,
      Provider<Json> jsonProvider) {
    this.okHttpProvider = okHttpProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public SpaceflightNewsApi get() {
    return provideApi(okHttpProvider.get(), jsonProvider.get());
  }

  public static AppModule_ProvideApiFactory create(Provider<OkHttpClient> okHttpProvider,
      Provider<Json> jsonProvider) {
    return new AppModule_ProvideApiFactory(okHttpProvider, jsonProvider);
  }

  public static SpaceflightNewsApi provideApi(OkHttpClient okHttp, Json json) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideApi(okHttp, json));
  }
}
