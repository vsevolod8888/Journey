package com.sever.journey.domain.usecase

import com.sever.journey.data.datastore.SettingsDataStore
import javax.inject.Inject

class ToggleThemeUseCase @Inject constructor(
    private val settingsData: SettingsDataStore
) {
    suspend operator fun invoke(theme: Boolean) {
        settingsData.markThemeManuallySet()
        settingsData.saveIsThemeDarkDataStore(theme)
    }
}
