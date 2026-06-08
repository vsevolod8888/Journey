package com.sever.journey.presentation.routesbigcalendar

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sever.journey.domain.model.LoadingState
import com.sever.journey.presentation.RoutesViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.sever.journey.core.ui.R
import com.sever.journey.TextStyleLocal
import com.sever.journey.analytics.AnalyticsService
import com.sever.journey.data.room.RouteEntity
import com.sever.journey.presentation.calendar.MyCalendarBig
import com.sever.journey.presentation.calendar.today
import com.sever.journey.presentation.common.NoInternetPicture
import com.sever.journey.presentation.common.NoRoutesText
import com.sever.journey.presentation.dialogs.DeleteRouteDialog
import com.sever.journey.presentation.routessmallcalendar.BackgroundHolderWhenDelete
import com.sever.journey.presentation.routessmallcalendar.RouteHolder
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoutesBigScreen(viewModel: RoutesViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()
    var deletedMatch by remember { mutableStateOf<RouteEntity?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var filteredRoutes by remember { mutableStateOf<List<RouteEntity>>(emptyList()) }
    var selectedDayToEpoch by remember { mutableStateOf<Int?>(null) }
    var selectedDay by remember { mutableStateOf<LocalDate?>(LocalDate.today()) }
    val today = LocalDate.today()

    LaunchedEffect(Unit) {
        AnalyticsService.logScreenView("BigCalendarScreen")
    }

    LaunchedEffect(uiState.routes, selectedDay, selectedDayToEpoch) {
        filteredRoutes = if (selectedDayToEpoch == null)
            uiState.routes.filter { it.epochDays.toLong() == today.toEpochDays() }
        else
            uiState.routes.filter { it.epochDays == selectedDayToEpoch }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.routes), style = TextStyleLocal.semibold20)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.primary
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
                NoInternetPicture(padding)
            }

            LoadingState.DONE -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(440.dp)
                            ) {
                                MyCalendarBig(uiState.routes, onDateSelected = { selectedDate ->
                                    selectedDayToEpoch = selectedDate.toEpochDays().toInt()
                                    selectedDay = selectedDate
                                    filteredRoutes =
                                        uiState.routes.filter { it.epochDays.toLong() == selectedDate.toEpochDays() }
                                })
                            }
                        }
                        if (filteredRoutes.isNotEmpty()) {
                            items(
                                items = filteredRoutes,
                                key = { it.id }
                            ) { selectedRoute ->
                                val swipeState = rememberSwipeToDismissBoxState(
                                    initialValue = SwipeToDismissBoxValue.Settled,
                                    confirmValueChange = {
                                        if (it == SwipeToDismissBoxValue.EndToStart || it == SwipeToDismissBoxValue.StartToEnd) {
                                            deletedMatch = selectedRoute
                                            showDialog = true
                                            false
                                        } else {
                                            true
                                        }
                                    },
                                )
                                SwipeToDismissBox(
                                    modifier = Modifier.fillMaxWidth(),
                                    state = swipeState,
                                    enableDismissFromStartToEnd = true,
                                    enableDismissFromEndToStart = true,
                                    gesturesEnabled = true,
                                    backgroundContent = {
                                        BackgroundHolderWhenDelete()
                                    },
                                ) {
                                    if (deletedMatch == selectedRoute) {
                                        BackgroundHolderWhenDelete()
                                    } else {
                                        RouteHolder(routeEntity = selectedRoute, navController)
                                    }
                                }
                            }
                        } else {
                            item {
                                NoRoutesText(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 50.dp),
                                    if (selectedDay == today) {
                                        stringResource(R.string.therearenoroutesfortoday)
                                    } else {
                                        stringResource(R.string.therearenoroutesonthisdate)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showDialog && deletedMatch != null) {
            DeleteRouteDialog(
                title = stringResource(R.string.tittledeleteallert),
                message = stringResource(R.string.areyousurewanttodeleteroute) + " ${deletedMatch?.recordRouteName}?",
                confirmText = stringResource(R.string.delete),
                dismissText = stringResource(R.string.cancel),
                onConfirm = {
                    AnalyticsService.logRouteDeleted(deletedMatch?.recordRouteName, "BigCalendarScreen")
                    deletedMatch?.let { viewModel.deleteRoute(it.id) }
                    deletedMatch = null
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                    deletedMatch = null
                }
            )
        }
    }
}





