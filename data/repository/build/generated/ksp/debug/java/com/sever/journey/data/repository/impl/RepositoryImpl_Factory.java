package com.sever.journey.data.repository.impl;

import com.sever.journey.data.room.Database;
import com.sever.journey.internetconnection.MyConnectivityManager;
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
public final class RepositoryImpl_Factory implements Factory<RepositoryImpl> {
  private final Provider<Database> databasseProvider;

  private final Provider<MyConnectivityManager> connectivityManagerProvider;

  private RepositoryImpl_Factory(Provider<Database> databasseProvider,
      Provider<MyConnectivityManager> connectivityManagerProvider) {
    this.databasseProvider = databasseProvider;
    this.connectivityManagerProvider = connectivityManagerProvider;
  }

  @Override
  public RepositoryImpl get() {
    return newInstance(databasseProvider.get(), connectivityManagerProvider.get());
  }

  public static RepositoryImpl_Factory create(Provider<Database> databasseProvider,
      Provider<MyConnectivityManager> connectivityManagerProvider) {
    return new RepositoryImpl_Factory(databasseProvider, connectivityManagerProvider);
  }

  public static RepositoryImpl newInstance(Database databasse,
      MyConnectivityManager connectivityManager) {
    return new RepositoryImpl(databasse, connectivityManager);
  }
}
