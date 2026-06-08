package com.sever.journey.presentation;

import com.sever.journey.data.repository.Repository;
import com.sever.journey.domain.usecase.DeleteAllRoutesUseCase;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<Repository> repositoryProvider;

  private final Provider<DeleteAllRoutesUseCase> deleteAllRoutesUseCaseProvider;

  private SettingsViewModel_Factory(Provider<Repository> repositoryProvider,
      Provider<DeleteAllRoutesUseCase> deleteAllRoutesUseCaseProvider) {
    this.repositoryProvider = repositoryProvider;
    this.deleteAllRoutesUseCaseProvider = deleteAllRoutesUseCaseProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(repositoryProvider.get(), deleteAllRoutesUseCaseProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<Repository> repositoryProvider,
      Provider<DeleteAllRoutesUseCase> deleteAllRoutesUseCaseProvider) {
    return new SettingsViewModel_Factory(repositoryProvider, deleteAllRoutesUseCaseProvider);
  }

  public static SettingsViewModel newInstance(Repository repository,
      DeleteAllRoutesUseCase deleteAllRoutesUseCase) {
    return new SettingsViewModel(repository, deleteAllRoutesUseCase);
  }
}
