package com.sever.journey.domain.usecase

import com.sever.journey.data.repository.Repository
import com.sever.journey.data.room.CoordinatesEntity
import com.sever.journey.data.room.RouteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

data class RouteWithCoordinates(
    val routeId: Long,
    val route: RouteEntity?,
    val coordinates: List<CoordinatesEntity>
)

class LoadAllRoutesForMapUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<RouteWithCoordinates>> {
        return repository.getOnlyIdList().map { ids ->
            ids.map { id ->
                val entity = repository.routeById(id)
                val coords = repository.coordtListLiveFlow(id).firstOrNull() ?: emptyList()
                RouteWithCoordinates(id, entity, coords)
            }
        }
    }
}
