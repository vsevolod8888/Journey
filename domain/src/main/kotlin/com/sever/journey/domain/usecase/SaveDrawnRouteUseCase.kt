package com.sever.journey.domain.usecase

import com.sever.journey.data.repository.Repository
import com.sever.journey.data.room.RouteEntity
import javax.inject.Inject

class SaveDrawnRouteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(name: String, recordNumber: Long, length: String) {
        val epochDays = recordNumber / 86400000
        val route = RouteEntity(
            id = recordNumber,
            epochDays = epochDays.toInt(),
            lenght = length,
            isDrawing = true,
            checkTime = System.currentTimeMillis(),
            recordRouteName = name,
            isClicked = false
        )
        repository.insertRoute(route)
    }
}
