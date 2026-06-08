package com.sever.journey.presentation;

import com.sever.journey.domain.usecase.DeleteRouteUseCase;
import com.sever.journey.domain.usecase.ObserveRoutesWithNetworkUseCase;
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
public final class RoutesViewModel_Factory implements Factory<RoutesViewModel> {
  private final Provider<ObserveRoutesWithNetworkUseCase> observeRoutesWithNetworkUseCaseProvider;

  private final Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider;

  private RoutesViewModel_Factory(
      Provider<ObserveRoutesWithNetworkUseCase> observeRoutesWithNetworkUseCaseProvider,
      Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider) {
    this.observeRoutesWithNetworkUseCaseProvider = observeRoutesWithNetworkUseCaseProvider;
    this.deleteRouteUseCaseProvider = deleteRouteUseCaseProvider;
  }

  @Override
  public RoutesViewModel get() {
    return newInstance(observeRoutesWithNetworkUseCaseProvider.get(), deleteRouteUseCaseProvider.get());
  }

  public static RoutesViewModel_Factory create(
      Provider<ObserveRoutesWithNetworkUseCase> observeRoutesWithNetworkUseCaseProvider,
      Provider<DeleteRouteUseCase> deleteRouteUseCaseProvider) {
    return new RoutesViewModel_Factory(observeRoutesWithNetworkUseCaseProvider, deleteRouteUseCaseProvider);
  }

  public static RoutesViewModel newInstance(
      ObserveRoutesWithNetworkUseCase observeRoutesWithNetworkUseCase,
      DeleteRouteUseCase deleteRouteUseCase) {
    return new RoutesViewModel(observeRoutesWithNetworkUseCase, deleteRouteUseCase);
  }
}
