package com.sever.journey.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sever.journey.data.datastore.SettingsDataStore
import com.sever.journey.data.repository.Repository
import com.sever.journey.domain.usecase.ToggleThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val settingsData: SettingsDataStore,
    private val repository: Repository,
    private val toggleThemeUseCase: ToggleThemeUseCase
) : ViewModel() {

    val isNetworkAvailable: StateFlow<Boolean> = repository.isConnectedFlow

    val isThemeDark: StateFlow<Boolean> = settingsData.isThemeDarkDataStore
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isThemeManuallySet: StateFlow<Boolean> = settingsData.isThemeManuallySet
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    suspend fun updateTheme(theme: Boolean) {
        toggleThemeUseCase(theme)
    }
}
