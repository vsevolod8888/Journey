package com.sever.journey.data.repository.impl

import androidx.room.withTransaction
import com.sever.journey.data.repository.Repository
import com.sever.journey.data.room.CoordinatesEntity
import com.sever.journey.data.room.Database
import com.sever.journey.data.room.RouteEntity
import com.sever.journey.internetconnection.MyConnectivityManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val databasse: Database,
    private val connectivityManager: MyConnectivityManager
) : Repository {

    override val isConnectedFlow = connectivityManager.connectionAsStateFlow
    override suspend fun updateFutureMatches() {
        if (!connectivityManager.isConnected) {
            return
        }
    }

    override suspend fun insertRoute(newRoute: RouteEntity) {
        databasse.dao.insertRoute(newRoute)
    }
    override fun allRoutesFlow(): Flow<List<RouteEntity>> {
        return databasse.dao.getRoutes()
    }
    override suspend fun insertCoord(c: CoordinatesEntity) {
        databasse.dao.insertCoord(c)
    }

    override fun coordtListLiveFlow(routeId: Long): Flow<List<CoordinatesEntity>> {
        return databasse.dao.getListByUnicalRecordNumber(routeId)
    }

    override suspend fun deleteRouteAndRecordNumberTogether(id: Long) {
        databasse.withTransaction {
            databasse.dao.deleteRouteById(id)
            databasse.dao.deleteCoordByRecordNumber(id)
        }
    }



    override suspend fun routeById(routeId: Long): RouteEntity? {
        return databasse.dao.routeById(routeId)
    }

    override suspend fun deleteAllRoutesAndCoords() {
        databasse.withTransaction {
            databasse.dao.deleteAllRoutes()
            databasse.dao.deleteAllCoords()
        }
    }

    override fun getOnlyIdList(): Flow<List<Long>> {
        return databasse.dao.getOnlyIdList()
    }

    companion object {
        const val TAG = "RepositoryImpl"
    }
}