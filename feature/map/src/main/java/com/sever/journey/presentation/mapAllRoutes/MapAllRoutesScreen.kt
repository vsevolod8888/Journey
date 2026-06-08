package com.sever.journey.presentation.mapAllRoutes

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.sever.journey.core.ui.R
import com.sever.journey.analytics.AnalyticsService
import com.sever.journey.domain.model.LoadingState
import com.sever.journey.presentation.MapAllRoutesViewModel
import com.sever.journey.permissions.LocationPermissionHandler
import com.sever.journey.presentation.dialogs.DeleteRouteDialog
import com.sever.journey.presentation.mapNewRoute.POSITION_KYIV
import com.sever.journey.utils.formatEpochDays
import com.sever.journey.utils.getBitmapDescriptor
import com.sever.journey.utils.shortenString

@Composable
fun MapAllRoutesScreen(
    viewModel: MapAllRoutesViewModel,
    navController: NavHostController
) {
    var locationPermissionGranted by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedRouteName by remember { mutableStateOf<String?>(null) }
    var selectedRouteId by remember { mutableStateOf<Long?>(null) }
    var isMapLoaded by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()
    val routeCoordinatesMap by viewModel.routeCoordinatesMap.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(POSITION_KYIV, 10f)
    }

    val stringDeleteHelper = stringResource(R.string.clickwindowtodeleteroute)

    LaunchedEffect(Unit) {
        AnalyticsService.logScreenView("MapAllRoutesScreen")
    }
    LocationPermissionHandler(
        onPermissionResult = { isGranted -> locationPermissionGranted = isGranted },
        onLocationReceived = { latLng ->
            cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
        }
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        when (uiState.loadingState) {
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
                        onMapLoaded = { isMapLoaded = true },
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(isMyLocationEnabled = locationPermissionGranted),
                        onMapClick = {}
                    ) {
                        routeCoordinatesMap.forEach { (routeId, routePoints) ->
                            if (routePoints.isNotEmpty()) {
                                val routeEntity = uiState.routes[routeId]
                                val customIcon = getBitmapDescriptor(R.drawable.ic_circle_)
                                val markerState = remember(routeId) { MarkerState(position = routePoints.first()) }
                                Marker(
                                    state = markerState,
                                    title = routeEntity?.recordRouteName?.let { shortenString(it) } + " • ${routeEntity?.lenght}",
                                    snippet = "${
                                        routeEntity?.epochDays?.let { formatEpochDays(it) }
                                    } • $stringDeleteHelper",
                                    icon = customIcon,
                                    onClick = { marker ->
                                        selectedRouteId = if (selectedRouteId == routeId) null else routeId
                                        marker.showInfoWindow()
                                        true
                                    },
                                    onInfoWindowClick = {
                                        selectedRouteName = uiState.routes[selectedRouteId]?.recordRouteName
                                        showDialog = true
                                    }
                                )

                                val isDrawing = routeEntity?.isDrawing ?: false
                                val polylineWidth = if (selectedRouteId == routeId) 20f else 5f

                                if (routePoints.size > 1) {
                                    Polyline(
                                        points = routePoints,
                                        color = if (isDrawing) Color.Red else Color.Blue,
                                        width = polylineWidth
                                    )
                                }
                            }
                        }
                    }
                    if (showDialog) {
                        DeleteRouteDialog(
                            title = stringResource(R.string.tittledeleteallert),
                            message = stringResource(R.string.areyousurewanttodeleteroute) + " ${selectedRouteName}?",
                            confirmText = stringResource(R.string.delete),
                            dismissText = stringResource(R.string.cancel),
                            onConfirm = {
                                AnalyticsService.logRouteDeleted(selectedRouteName, "MapAllRoutesScreen")
                                selectedRouteId?.let { viewModel.deleteRoute(it) }
                                selectedRouteId = null
                                showDialog = false
                            },
                            onDismiss = {
                                showDialog = false
                            }
                        )
                    }
                }
            }
        }
    }

    BackHandler {
        navController.popBackStack()
    }
}









