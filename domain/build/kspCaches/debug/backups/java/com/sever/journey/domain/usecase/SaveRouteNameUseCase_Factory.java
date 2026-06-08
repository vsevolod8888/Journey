package com.sever.journey.domain.usecase;

import com.sever.journey.data.datastore.SettingsDataStore;
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
public final class SaveRouteNameUseCase_Factory implements Factory<SaveRouteNameUseCase> {
  private final Provider<SettingsDataStore> settingsDataProvider;

  private SaveRouteNameUseCase_Factory(Provider<SettingsDataStore> settingsDataProvider) {
    this.settingsDataProvider = settingsDataProvider;
  }

  @Override
  public SaveRouteNameUseCase get() {
    return newInstance(settingsDataProvider.get());
  }

  public static SaveRouteNameUseCase_Factory create(
      Provider<SettingsDataStore> settingsDataProvider) {
    return new SaveRouteNameUseCase_Factory(settingsDataProvider);
  }

  public static SaveRouteNameUseCase newInstance(SettingsDataStore settingsData) {
    return new SaveRouteNameUseCase(settingsData);
  }
}
