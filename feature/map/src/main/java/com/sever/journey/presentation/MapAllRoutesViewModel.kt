package com.sever.journey.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sever.journey.data.room.RouteEntity
import com.sever.journey.domain.model.LoadingState
import com.sever.journey.domain.usecase.DeleteRouteUseCase
import com.sever.journey.domain.usecase.LoadAllRoutesForMapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MapAllRoutesUiState(
    val loadingState: LoadingState = LoadingState.LOADING,
    val routes: Map<Long, RouteEntity?> = emptyMap(),
)

@HiltViewModel
class MapAllRoutesViewModel @Inject constructor(
    private val loadAllRoutesForMapUseCase: LoadAllRoutesForMapUseCase,
    private val deleteRouteUseCase: DeleteRouteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapAllRoutesUiState())
    val uiState: StateFlow<MapAllRoutesUiState> = _uiState.asStateFlow()

    private val _routeCoordinatesMap = MutableStateFlow<Map<Long, List<com.google.android.gms.maps.model.LatLng>>>(emptyMap())
    val routeCoordinatesMap: StateFlow<Map<Long, List<com.google.android.gms.maps.model.LatLng>>> = _routeCoordinatesMap.asStateFlow()

    init {
        loadRoutes()
    }

    private fun loadRoutes() {
        viewModelScope.launch {
            loadAllRoutesForMapUseCase().collect { items ->
                val routesMap = mutableMapOf<Long, RouteEntity?>()
                val coordsMap = mutableMapOf<Long, List<com.google.android.gms.maps.model.LatLng>>()

                for (item in items) {
                    routesMap[item.routeId] = item.route
                    coordsMap[item.routeId] = item.coordinates.map {
                        com.google.android.gms.maps.model.LatLng(it.lattitude, it.longittude)
                    }
                }

                _routeCoordinatesMap.value = coordsMap
                _uiState.value = MapAllRoutesUiState(
                    loadingState = LoadingState.DONE,
                    routes = routesMap
                )
            }
        }
    }

    fun deleteRoute(id: Long) {
        viewModelScope.launch {
            deleteRouteUseCase(id)
        }
    }
}
