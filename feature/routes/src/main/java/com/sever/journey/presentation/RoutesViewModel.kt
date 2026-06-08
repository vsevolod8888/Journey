package com.sever.journey.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sever.journey.data.room.RouteEntity
import com.sever.journey.domain.model.LoadingState
import com.sever.journey.domain.usecase.DeleteRouteUseCase
import com.sever.journey.domain.usecase.ObserveRoutesWithNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RoutesUiState(
    val loadingState: LoadingState = LoadingState.LOADING,
    val routes: List<RouteEntity> = emptyList(),
)

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val observeRoutesWithNetworkUseCase: ObserveRoutesWithNetworkUseCase,
    private val deleteRouteUseCase: DeleteRouteUseCase
) : ViewModel() {

    val uiState: StateFlow<RoutesUiState> = observeRoutesWithNetworkUseCase()
        .map { (isConnected, routes) ->
            when {
                !isConnected -> RoutesUiState(loadingState = LoadingState.ERROR)
                else -> RoutesUiState(loadingState = LoadingState.DONE, routes = routes)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RoutesUiState())

    fun deleteRoute(id: Long) {
        viewModelScope.launch {
            deleteRouteUseCase(id)
        }
    }
}
