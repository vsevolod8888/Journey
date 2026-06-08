package com.sever.journey.domain.usecase

import com.sever.journey.data.repository.Repository
import javax.inject.Inject

class DeleteRouteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteRouteAndRecordNumberTogether(id)
    }
}
