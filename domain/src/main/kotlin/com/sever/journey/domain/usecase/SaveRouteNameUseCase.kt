package com.sever.journey.domain.usecase

import com.sever.journey.data.datastore.SettingsDataStore
import javax.inject.Inject

class SaveRouteNameUseCase @Inject constructor(
    private val settingsData: SettingsDataStore
) {
    suspend operator fun invoke(name: String) {
        settingsData.saveRouteNameDataStore(name)
    }
}
