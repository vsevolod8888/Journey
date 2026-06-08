package com.sever.journey.di

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.sever.journey.data.repository.Repository
import com.sever.journey.data.repository.impl.RepositoryImpl
import com.sever.journey.data.room.Database
import com.sever.journey.data.room.MIGRATION_1_2
import com.sever.journey.internetconnection.MyConnectivityManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "data"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("ServiceCast")
    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): MyConnectivityManager {
        return MyConnectivityManager(context, GlobalScope)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}