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
public final class SaveDrawCoordUseCase_Factory implements Factory<SaveDrawCoordUseCase> {
  private final Provider<Repository> repositoryProvider;

  private SaveDrawCoordUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SaveDrawCoordUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SaveDrawCoordUseCase_Factory create(Provider<Repository> repositoryProvider) {
    return new SaveDrawCoordUseCase_Factory(repositoryProvider);
  }

  public static SaveDrawCoordUseCase newInstance(Repository repository) {
    return new SaveDrawCoordUseCase(repository);
  }
}
