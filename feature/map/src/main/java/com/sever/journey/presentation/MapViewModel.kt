package com.sever.journey.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sever.journey.data.datastore.SettingsDataStore
import com.sever.journey.data.room.CoordinatesEntity
import com.sever.journey.data.room.RouteEntity
import kotlinx.coroutines.flow.Flow
import com.sever.journey.domain.model.LoadingState
import com.sever.journey.domain.usecase.DeleteRouteUseCase
import com.sever.journey.domain.usecase.LoadRouteForDisplayUseCase
import com.sever.journey.domain.usecase.ObserveCoordinatesForRouteUseCase
import com.sever.journey.domain.usecase.SaveDrawCoordUseCase
import com.sever.journey.domain.usecase.SaveDrawnRouteUseCase
import com.sever.journey.domain.usecase.SaveRouteNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MapReadyUiState(
    val loadingState: LoadingState = LoadingState.LOADING,
    val routeEntity: RouteEntity? = null,
    val coordinates: List<CoordinatesEntity> = emptyList(),
)

@HiltViewModel
class MapViewModel @Inject constructor(
    private val settingsData: SettingsDataStore,
    private val deleteRouteUseCase: DeleteRouteUseCase,
    private val loadRouteForDisplayUseCase: LoadRouteForDisplayUseCase,
    private val observeCoordinatesForRouteUseCase: ObserveCoordinatesForRouteUseCase,
    private val saveRouteNameUseCase: SaveRouteNameUseCase,
    private val saveDrawCoordUseCase: SaveDrawCoordUseCase,
    private val saveDrawnRouteUseCase: SaveDrawnRouteUseCase
) : ViewModel() {

    private val _readyUiState = MutableStateFlow(MapReadyUiState())
    val readyUiState: StateFlow<MapReadyUiState> = _readyUiState.asStateFlow()

    fun loadRouteForDisplay(routeId: Long) {
        viewModelScope.launch {
            loadRouteForDisplayUseCase(routeId).collect { (entity, coords) ->
                _readyUiState.value = MapReadyUiState(
                    loadingState = LoadingState.DONE,
                    routeEntity = entity,
                    coordinates = coords
                )
            }
        }
    }

    val routeId: StateFlow<Long> = settingsData.routeId
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0L)

    suspend fun updateRouteId(newId: Long) {
        settingsData.saveRouteId(newId)
    }

    suspend fun clearRouteId() {
        settingsData.saveRouteId(0L)
    }

    suspend fun saveRouteName(routeName: String) {
        saveRouteNameUseCase(routeName)
    }

    suspend fun saveDrawCoord(lat: Double, lon: Double, checkTime: Long, recNum: Long) {
        saveDrawCoordUseCase(lat, lon, checkTime, recNum)
    }

    suspend fun saveDrawRoute(nameOfDrRoute: String, numbOfRecord: Long, lenght: String) {
        saveDrawnRouteUseCase(nameOfDrRoute, numbOfRecord, lenght)
    }

    fun deleteRoute(id: Long) {
        viewModelScope.launch {
            deleteRouteUseCase(id)
        }
    }

    fun coordtListLiveFlow(routeId: Long): Flow<List<CoordinatesEntity>> {
        return observeCoordinatesForRouteUseCase(routeId)
    }
}
