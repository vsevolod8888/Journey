package com.sever.journey.presentation;

import com.sever.journey.domain.usecase.DeleteRouteUseCase;
import com.sever.journey.domain.usecase.LoadAllRoutesForMapUseCase;
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
public final class MapAllRoutesViewModel_Factory implements Factory<MapAllRoutesViewModel> {
  private final Provider<LoadAllRoutesForMapUseCase> loadAllRoutesForMapUseCaseProvider;

  private final Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider;

  private MapAllRoutesViewModel_Factory(
      Provider<LoadAllRoutesForMapUseCase> loadAllRoutesForMapUseCaseProvider,
      Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider) {
    this.loadAllRoutesForMapUseCaseProvider = loadAllRoutesForMapUseCaseProvider;
    this.deleteRouteUseCaseProvider = deleteRouteUseCaseProvider;
  }

  @Override
  public MapAllRoutesViewModel get() {
    return newInstance(loadAllRoutesForMapUseCaseProvider.get(), deleteRouteUseCaseProvider.get());
  }

  public static MapAllRoutesViewModel_Factory create(
      Provider<LoadAllRoutesForMapUseCase> loadAllRoutesForMapUseCaseProvider,
      Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider) {
    return new MapAllRoutesViewModel_Factory(loadAllRoutesForMapUseCaseProvider, deleteRouteUseCaseProvider);
  }

  public static MapAllRoutesViewModel newInstance(
      LoadAllRoutesForMapUseCase loadAllRoutesForMapUseCase,
      DeleteRouteUseCase deleteRouteUseCase) {
    return new MapAllRoutesViewModel(loadAllRoutesForMapUseCase, deleteRouteUseCase);
  }
}
