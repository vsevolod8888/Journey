package com.sever.journey.domain.usecase

import com.sever.journey.data.repository.Repository
import com.sever.journey.data.room.CoordinatesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCoordinatesForRouteUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(routeId: Long): Flow<List<CoordinatesEntity>> {
        return repository.coordtListLiveFlow(routeId)
    }
}
