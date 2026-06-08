package com.sever.journey.domain.usecase

import com.sever.journey.data.repository.Repository
import com.sever.journey.data.room.CoordinatesEntity
import com.sever.journey.data.room.RouteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadRouteForDisplayUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(routeId: Long): Flow<Pair<RouteEntity?, List<CoordinatesEntity>>> {
        return combine(
            flow { emit(repository.routeById(routeId)) },
            repository.coordtListLiveFlow(routeId)
        ) { entity, coords ->
            entity to coords
        }
    }
}
