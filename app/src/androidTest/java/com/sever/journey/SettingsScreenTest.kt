package com.sever.journey

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sever.journey.presentation.settings.SettingsScreen
import com.sever.journey.presentation.AppViewModel
import com.sever.journey.presentation.SettingsViewModel
import com.sever.journey.presentation.MapViewModel
import com.sever.journey.presentation.bottomnavigation.NavigationItem
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import com.sever.journey.presentation.mapAllRoutes.MapAllRoutesScreen
import io.mockk.every
import kotlinx.coroutines.flow.MutableStateFlow

@RunWith(AndroidJUnit4::class)
class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var appViewModel: AppViewModel
    private lateinit var mapViewModel: MapViewModel
    val isNetworkAvailableFlow = MutableStateFlow(true)
    val isThemeDarkFlow = MutableStateFlow(false)

    @Before
    fun setUp() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext()).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }
        settingsViewModel = mockk(relaxed = true)
        mapViewModel = mockk(relaxed = true)
        appViewModel = mockk {
            every { isNetworkAvailable } returns isNetworkAvailableFlow
            every { isThemeDark } returns isThemeDarkFlow
        }
    }

    @Test
    fun testNavigation() {
        composeTestRule.setContent {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.Settings.route
            ) {
                composable(NavigationItem.Settings.route) {
                    SettingsScreen(viewModel = settingsViewModel, appViewModel = appViewModel, navController = navController)
                }
                composable(NavigationItem.MapAll.route) {
                    MapAllRoutesScreen(viewModel = mapViewModel, navController = navController)
                }
            }
        }

        composeTestRule.onNodeWithText("Show all routes").assertIsDisplayed().performClick()

        assertThat(navController.currentDestination?.route).isEqualTo(NavigationItem.MapAll.route)
    }
}


