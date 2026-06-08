package com.sever.journey.domain.usecase;

import com.sever.journey.data.repository.Repository;
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
public final class ObserveCoordinatesForRouteUseCase_Factory implements Factory<ObserveCoordinatesForRouteUseCase> {
  private final Provider<Repository> repositoryProvider;

  private ObserveCoordinatesForRouteUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObserveCoordinatesForRouteUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObserveCoordinatesForRouteUseCase_Factory create(
      Provider<Repository> repositoryProvider) {
    return new ObserveCoordinatesForRouteUseCase_Factory(repositoryProvider);
  }

  public static ObserveCoordinatesForRouteUseCase newInstance(Repository repository) {
    return new ObserveCoordinatesForRouteUseCase(repository);
  }
}
