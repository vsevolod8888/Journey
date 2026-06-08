package com.sever.journey

import com.sever.journey.data.datastore.SettingsDataStore
import com.sever.journey.domain.usecase.DeleteRouteUseCase
import com.sever.journey.domain.usecase.LoadRouteForDisplayUseCase
import com.sever.journey.domain.usecase.ObserveCoordinatesForRouteUseCase
import com.sever.journey.domain.usecase.SaveDrawCoordUseCase
import com.sever.journey.domain.usecase.SaveDrawnRouteUseCase
import com.sever.journey.domain.usecase.SaveRouteNameUseCase
import com.sever.journey.presentation.MapViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class MyViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MapViewModel
    private val settingsData: SettingsDataStore = mockk(relaxed = true)
    private val deleteRouteUseCase: DeleteRouteUseCase = mockk(relaxed = true)
    private val loadRouteForDisplayUseCase: LoadRouteForDisplayUseCase = mockk(relaxed = true)
    private val observeCoordinatesForRouteUseCase: ObserveCoordinatesForRouteUseCase = mockk(relaxed = true)
    private val saveRouteNameUseCase: SaveRouteNameUseCase = mockk(relaxed = true)
    private val saveDrawCoordUseCase: SaveDrawCoordUseCase = mockk(relaxed = true)
    private val saveDrawnRouteUseCase: SaveDrawnRouteUseCase = mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel = MapViewModel(
            settingsData,
            deleteRouteUseCase,
            loadRouteForDisplayUseCase,
            observeCoordinatesForRouteUseCase,
            saveRouteNameUseCase,
            saveDrawCoordUseCase,
            saveDrawnRouteUseCase
        )
    }

    @Test
    fun `updateRouteId saves new value`() = runTest {
        viewModel.updateRouteId(123L)
        coVerify { settingsData.saveRouteId(123L) }
    }

    @Test
    fun `saveDrawRoute creates route via use case`() = runTest {
        val routeName = "Test Route"
        val recordNumber = 86400000L
        val length = "10km"
        viewModel.saveDrawRoute(routeName, recordNumber, length)
        coVerify { saveDrawnRouteUseCase(routeName, recordNumber, length) }
    }

    @Test
    fun `deleteRoute calls use case`() = runTest {
        viewModel.deleteRoute(42L)
        advanceUntilIdle()
        coVerify { deleteRouteUseCase(42L) }
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val dispatcher: kotlinx.coroutines.test.TestDispatcher = StandardTestDispatcher()
) : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                Dispatchers.setMain(dispatcher)
                try {
                    base.evaluate()
                } finally {
                    Dispatchers.resetMain()
                }
            }
        }
    }
}
