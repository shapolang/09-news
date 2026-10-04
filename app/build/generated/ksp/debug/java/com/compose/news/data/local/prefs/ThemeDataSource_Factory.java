package com.compose.news.data.local.prefs;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
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
public final class ThemeDataSource_Factory implements Factory<ThemeDataSource> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  private ThemeDataSource_Factory(Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public ThemeDataSource get() {
    return newInstance(dataStoreProvider.get());
  }

  public static ThemeDataSource_Factory create(Provider<DataStore<Preferences>> dataStoreProvider) {
    return new ThemeDataSource_Factory(dataStoreProvider);
  }

  public static ThemeDataSource newInstance(DataStore<Preferences> dataStore) {
    return new ThemeDataSource(dataStore);
  }
}
