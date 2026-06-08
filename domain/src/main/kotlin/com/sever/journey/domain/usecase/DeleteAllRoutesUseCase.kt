package com.sever.journey.domain.usecase

import com.sever.journey.data.repository.Repository
import javax.inject.Inject

class DeleteAllRoutesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.deleteAllRoutesAndCoords()
    }
}
