package com.sever.journey.presentation

import androidx.lifecycle.ViewModel
import com.sever.journey.data.repository.Repository
import com.sever.journey.domain.usecase.DeleteAllRoutesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: Repository,
    private val deleteAllRoutesUseCase: DeleteAllRoutesUseCase
) : ViewModel() {

    val isNetworkAvailable: StateFlow<Boolean> = repository.isConnectedFlow

    suspend fun deleteAllRoutes() {
        deleteAllRoutesUseCase()
    }
}
