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
public final class ToggleThemeUseCase_Factory implements Factory<ToggleThemeUseCase> {
  private final Provider<SettingsDataStore> settingsDataProvider;

  private ToggleThemeUseCase_Factory(Provider<SettingsDataStore> settingsDataProvider) {
    this.settingsDataProvider = settingsDataProvider;
  }

  @Override
  public ToggleThemeUseCase get() {
    return newInstance(settingsDataProvider.get());
  }

  public static ToggleThemeUseCase_Factory create(
      Provider<SettingsDataStore> settingsDataProvider) {
    return new ToggleThemeUseCase_Factory(settingsDataProvider);
  }

  public static ToggleThemeUseCase newInstance(SettingsDataStore settingsData) {
    return new ToggleThemeUseCase(settingsData);
  }
}
