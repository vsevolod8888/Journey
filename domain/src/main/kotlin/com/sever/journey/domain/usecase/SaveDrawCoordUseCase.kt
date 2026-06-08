package com.sever.journey.domain.usecase

import com.sever.journey.data.repository.Repository
import com.sever.journey.data.room.CoordinatesEntity
import javax.inject.Inject

class SaveDrawCoordUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(lat: Double, lon: Double, checkTime: Long, recNum: Long) {
        val coord = CoordinatesEntity(
            id = 0,
            checkTime = checkTime,
            recordNumber = recNum,
            lattitude = lat,
            longittude = lon
        )
        repository.insertCoord(coord)
    }
}
