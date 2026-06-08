package com.sever.journey.presentation;

import com.sever.journey.data.datastore.SettingsDataStore;
import com.sever.journey.domain.usecase.DeleteRouteUseCase;
import com.sever.journey.domain.usecase.LoadRouteForDisplayUseCase;
import com.sever.journey.domain.usecase.ObserveCoordinatesForRouteUseCase;
import com.sever.journey.domain.usecase.SaveDrawCoordUseCase;
import com.sever.journey.domain.usecase.SaveDrawnRouteUseCase;
import com.sever.journey.domain.usecase.SaveRouteNameUseCase;
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
public final class MapViewModel_Factory implements Factory<MapViewModel> {
  private final Provider<SettingsDataStore> settingsDataProvider;

  private final Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider;

  private final Provider<LoadRouteForDisplayUseCase> loadRouteForDisplayUseCaseProvider;

  private final Provider<ObserveCoordinatesForRouteUseCase> observeCoordinatesForRouteUseCaseProvider;

  private final Provider<SaveRouteNameUseCase> saveRouteNameUseCaseProvider;

  private final Provider<SaveDrawCoordUseCase> saveDrawCoordUseCaseProvider;

  private final Provider<SaveDrawnRouteUseCase> saveDrawnRouteUseCaseProvider;

  private MapViewModel_Factory(Provider<SettingsDataStore> settingsDataProvider,
      Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider,
      Provider<LoadRouteForDisplayUseCase> loadRouteForDisplayUseCaseProvider,
      Provider<ObserveCoordinatesForRouteUseCase> observeCoordinatesForRouteUseCaseProvider,
      Provider<SaveRouteNameUseCase> saveRouteNameUseCaseProvider,
      Provider<SaveDrawCoordUseCase> saveDrawCoordUseCaseProvider,
      Provider<SaveDrawnRouteUseCase> saveDrawnRouteUseCaseProvider) {
    this.settingsDataProvider = settingsDataProvider;
    this.deleteRouteUseCaseProvider = deleteRouteUseCaseProvider;
    this.loadRouteForDisplayUseCaseProvider = loadRouteForDisplayUseCaseProvider;
    this.observeCoordinatesForRouteUseCaseProvider = observeCoordinatesForRouteUseCaseProvider;
    this.saveRouteNameUseCaseProvider = saveRouteNameUseCaseProvider;
    this.saveDrawCoordUseCaseProvider = saveDrawCoordUseCaseProvider;
    this.saveDrawnRouteUseCaseProvider = saveDrawnRouteUseCaseProvider;
  }

  @Override
  public MapViewModel get() {
    return newInstance(settingsDataProvider.get(), deleteRouteUseCaseProvider.get(), loadRouteForDisplayUseCaseProvider.get(), observeCoordinatesForRouteUseCaseProvider.get(), saveRouteNameUseCaseProvider.get(), saveDrawCoordUseCaseProvider.get(), saveDrawnRouteUseCaseProvider.get());
  }

  public static MapViewModel_Factory create(Provider<SettingsDataStore> settingsDataProvider,
      Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider,
      Provider<LoadRouteForDisplayUseCase> loadRouteForDisplayUseCaseProvider,
      Provider<ObserveCoordinatesForRouteUseCase> observeCoordinatesForRouteUseCaseProvider,
      Provider<SaveRouteNameUseCase> saveRouteNameUseCaseProvider,
      Provider<SaveDrawCoordUseCase> saveDrawCoordUseCaseProvider,
      Provider<SaveDrawnRouteUseCase> saveDrawnRouteUseCaseProvider) {
    return new MapViewModel_Factory(settingsDataProvider, deleteRouteUseCaseProvider, loadRouteForDisplayUseCaseProvider, observeCoordinatesForRouteUseCaseProvider, saveRouteNameUseCaseProvider, saveDrawCoordUseCaseProvider, saveDrawnRouteUseCaseProvider);
  }

  public static MapViewModel newInstance(SettingsDataStore settingsData,
      DeleteRouteUseCase deleteRouteUseCase, LoadRouteForDisplayUseCase loadRouteForDisplayUseCase,
      ObserveCoordinatesForRouteUseCase observeCoordinatesForRouteUseCase,
      SaveRouteNameUseCase saveRouteNameUseCase, SaveDrawCoordUseCase saveDrawCoordUseCase,
      SaveDrawnRouteUseCase saveDrawnRouteUseCase) {
    return new MapViewModel(settingsData, deleteRouteUseCase, loadRouteForDisplayUseCase, observeCoordinatesForRouteUseCase, saveRouteNameUseCase, saveDrawCoordUseCase, saveDrawnRouteUseCase);
  }
}
