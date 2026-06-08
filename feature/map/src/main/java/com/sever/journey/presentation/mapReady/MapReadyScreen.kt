package com.sever.journey.presentation.mapReady

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.sever.journey.core.ui.R
import com.sever.journey.TextStyleLocal
import com.sever.journey.analytics.AnalyticsService
import com.sever.journey.permissions.LocationPermissionHandler
import com.sever.journey.domain.model.LoadingState
import com.sever.journey.presentation.MapViewModel
import com.sever.journey.presentation.bottomnavigation.NavigationItem
import com.sever.journey.presentation.mapDraw.calculateRouteLength
import com.sever.journey.presentation.mapNewRoute.POSITION_KYIV
import kotlinx.coroutines.launch

@Composable
fun MapReadyScreen(
    viewModel: MapViewModel,
    navController: NavHostController,
    routeId: Long,
    recordRouteName: String,
    onStartCounterService: () -> Unit
) {
    val context = LocalContext.current
    var locationPermissionGranted by remember { mutableStateOf(false) }
    var routeLength by remember { mutableStateOf("") }
    var scope = rememberCoroutineScope()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(POSITION_KYIV, 10f)
    }
    var markerLatLngList by remember { mutableStateOf<List<LatLng>>(emptyList()) }
    val kmText = stringResource(R.string.km)
    val metersText = stringResource(R.string.meters)

    val readyUiState by viewModel.readyUiState.collectAsState()

    LaunchedEffect(Unit) {
        AnalyticsService.logScreenView("MapReadyScreen")
        viewModel.updateRouteId(routeId)
        viewModel.loadRouteForDisplay(routeId)
    }

    LaunchedEffect(readyUiState.coordinates) {
        val coords = readyUiState.coordinates
        markerLatLngList = coords.map { LatLng(it.lattitude, it.longittude) }
        routeLength = calculateRouteLength(markerLatLngList, kmText, metersText)
        if (markerLatLngList.isNotEmpty()) {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(markerLatLngList.last(), 15f),
                durationMs = 200
            )
        }
    }

    LocationPermissionHandler(
        onPermissionResult = { isGranted -> locationPermissionGranted = isGranted },
        onLocationReceived = { latLng ->
            Log.d("zzz", " location : ${latLng.latitude}, ${latLng.longitude})")
            cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        when (readyUiState.loadingState) {
            LoadingState.LOADING -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            LoadingState.ERROR -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    com.sever.journey.presentation.common.NoInternetPicture(padding)
                }
            }

            LoadingState.DONE -> {
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(isMyLocationEnabled = locationPermissionGranted),
                    ) {
                        if (markerLatLngList.size > 1) {
                            Polyline(
                                points = markerLatLngList,
                                color = if (readyUiState.routeEntity?.isDrawing == true) Color.Red else Color.Blue,
                                width = 5f
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = routeLength,
                            style = TextStyleLocal.semibold18,
                            color = Color.Black,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(
                            modifier = Modifier.padding(16.dp),
                            onClick = {
                                scope.launch {
                                    when (readyUiState.routeEntity?.isDrawing) {
                                        true -> {
                                            AnalyticsService.logMapContinue("draw")
                                            viewModel.saveRouteName(recordRouteName)
                                            navController.navigate("${NavigationItem.MapDraw.route}/$recordRouteName") {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }

                                        else -> {
                                            AnalyticsService.logMapContinue("new_route")
                                            viewModel.saveRouteName(recordRouteName)
                                            navController.navigate("${NavigationItem.MapNew.route}/$recordRouteName") {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                            onStartCounterService()
                                        }
                                    }
                                }
                            },
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                                contentColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.surface,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.conti),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
    BackHandler {
        scope.launch {
            viewModel.updateRouteId(0L)
            navController.popBackStack()
        }
    }
}





