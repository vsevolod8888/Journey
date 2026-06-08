package com.sever.journey.domain.usecase

import com.sever.journey.data.repository.Repository
import com.sever.journey.data.room.RouteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ObserveRoutesWithNetworkUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Pair<Boolean, List<RouteEntity>>> {
        return combine(
            repository.isConnectedFlow,
            repository.allRoutesFlow()
        ) { isConnected, routes ->
            isConnected to routes
        }
    }
}
