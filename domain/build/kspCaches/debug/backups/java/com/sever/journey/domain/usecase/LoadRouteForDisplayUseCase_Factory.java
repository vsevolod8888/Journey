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
public final class LoadRouteForDisplayUseCase_Factory implements Factory<LoadRouteForDisplayUseCase> {
  private final Provider<Repository> repositoryProvider;

  private LoadRouteForDisplayUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LoadRouteForDisplayUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static LoadRouteForDisplayUseCase_Factory create(Provider<Repository> repositoryProvider) {
    return new LoadRouteForDisplayUseCase_Factory(repositoryProvider);
  }

  public static LoadRouteForDisplayUseCase newInstance(Repository repository) {
    return new LoadRouteForDisplayUseCase(repository);
  }
}
