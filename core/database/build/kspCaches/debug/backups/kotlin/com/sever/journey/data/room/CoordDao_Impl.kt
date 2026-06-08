package com.sever.journey.`data`.room

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CoordDao_Impl(
  __db: RoomDatabase,
) : CoordDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRouteEntity: EntityInsertAdapter<RouteEntity>

  private val __insertAdapterOfCoordinatesEntity: EntityInsertAdapter<CoordinatesEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRouteEntity = object : EntityInsertAdapter<RouteEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `route` (`id`,`epochDays`,`isDrawing`,`lenght`,`checktime`,`recordRouteName`,`isClicked`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RouteEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.epochDays.toLong())
        val _tmp: Int = if (entity.isDrawing) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindText(4, entity.lenght)
        statement.bindLong(5, entity.checkTime)
        statement.bindText(6, entity.recordRouteName)
        val _tmp_1: Int = if (entity.isClicked) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
      }
    }
    this.__insertAdapterOfCoordinatesEntity = object : EntityInsertAdapter<CoordinatesEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `coord` (`id`,`checktime`,`recordNumber`,`lattitude`,`longittude`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CoordinatesEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.checkTime)
        statement.bindLong(3, entity.recordNumber)
        statement.bindDouble(4, entity.lattitude)
        statement.bindDouble(5, entity.longittude)
      }
    }
  }

  public override suspend fun insertRoute(r: RouteEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRouteEntity.insert(_connection, r)
  }

  public override suspend fun insertCoord(c: CoordinatesEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCoordinatesEntity.insert(_connection, c)
  }

  public override fun getRoutes(): Flow<List<RouteEntity>> {
    val _sql: String = "select * from route"
    return createFlow(__db, false, arrayOf("route")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpochDays: Int = getColumnIndexOrThrow(_stmt, "epochDays")
        val _columnIndexOfIsDrawing: Int = getColumnIndexOrThrow(_stmt, "isDrawing")
        val _columnIndexOfLenght: Int = getColumnIndexOrThrow(_stmt, "lenght")
        val _columnIndexOfCheckTime: Int = getColumnIndexOrThrow(_stmt, "checktime")
        val _columnIndexOfRecordRouteName: Int = getColumnIndexOrThrow(_stmt, "recordRouteName")
        val _columnIndexOfIsClicked: Int = getColumnIndexOrThrow(_stmt, "isClicked")
        val _result: MutableList<RouteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RouteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpochDays: Int
          _tmpEpochDays = _stmt.getLong(_columnIndexOfEpochDays).toInt()
          val _tmpIsDrawing: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDrawing).toInt()
          _tmpIsDrawing = _tmp != 0
          val _tmpLenght: String
          _tmpLenght = _stmt.getText(_columnIndexOfLenght)
          val _tmpCheckTime: Long
          _tmpCheckTime = _stmt.getLong(_columnIndexOfCheckTime)
          val _tmpRecordRouteName: String
          _tmpRecordRouteName = _stmt.getText(_columnIndexOfRecordRouteName)
          val _tmpIsClicked: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsClicked).toInt()
          _tmpIsClicked = _tmp_1 != 0
          _item = RouteEntity(_tmpId,_tmpEpochDays,_tmpIsDrawing,_tmpLenght,_tmpCheckTime,_tmpRecordRouteName,_tmpIsClicked)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun routeById(routeId: Long): RouteEntity? {
    val _sql: String = "SELECT * FROM route WHERE id =?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, routeId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpochDays: Int = getColumnIndexOrThrow(_stmt, "epochDays")
        val _columnIndexOfIsDrawing: Int = getColumnIndexOrThrow(_stmt, "isDrawing")
        val _columnIndexOfLenght: Int = getColumnIndexOrThrow(_stmt, "lenght")
        val _columnIndexOfCheckTime: Int = getColumnIndexOrThrow(_stmt, "checktime")
        val _columnIndexOfRecordRouteName: Int = getColumnIndexOrThrow(_stmt, "recordRouteName")
        val _columnIndexOfIsClicked: Int = getColumnIndexOrThrow(_stmt, "isClicked")
        val _result: RouteEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpochDays: Int
          _tmpEpochDays = _stmt.getLong(_columnIndexOfEpochDays).toInt()
          val _tmpIsDrawing: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDrawing).toInt()
          _tmpIsDrawing = _tmp != 0
          val _tmpLenght: String
          _tmpLenght = _stmt.getText(_columnIndexOfLenght)
          val _tmpCheckTime: Long
          _tmpCheckTime = _stmt.getLong(_columnIndexOfCheckTime)
          val _tmpRecordRouteName: String
          _tmpRecordRouteName = _stmt.getText(_columnIndexOfRecordRouteName)
          val _tmpIsClicked: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsClicked).toInt()
          _tmpIsClicked = _tmp_1 != 0
          _result = RouteEntity(_tmpId,_tmpEpochDays,_tmpIsDrawing,_tmpLenght,_tmpCheckTime,_tmpRecordRouteName,_tmpIsClicked)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getListByUnicalRecordNumber(recordNumberId: Long?): Flow<List<CoordinatesEntity>> {
    val _sql: String = "SELECT * FROM coord WHERE recordNumber=? ORDER BY checktime "
    return createFlow(__db, false, arrayOf("coord")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (recordNumberId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, recordNumberId)
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCheckTime: Int = getColumnIndexOrThrow(_stmt, "checktime")
        val _columnIndexOfRecordNumber: Int = getColumnIndexOrThrow(_stmt, "recordNumber")
        val _columnIndexOfLattitude: Int = getColumnIndexOrThrow(_stmt, "lattitude")
        val _columnIndexOfLongittude: Int = getColumnIndexOrThrow(_stmt, "longittude")
        val _result: MutableList<CoordinatesEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CoordinatesEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpCheckTime: Long
          _tmpCheckTime = _stmt.getLong(_columnIndexOfCheckTime)
          val _tmpRecordNumber: Long
          _tmpRecordNumber = _stmt.getLong(_columnIndexOfRecordNumber)
          val _tmpLattitude: Double
          _tmpLattitude = _stmt.getDouble(_columnIndexOfLattitude)
          val _tmpLongittude: Double
          _tmpLongittude = _stmt.getDouble(_columnIndexOfLongittude)
          _item = CoordinatesEntity(_tmpId,_tmpCheckTime,_tmpRecordNumber,_tmpLattitude,_tmpLongittude)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getOnlyIdList(): Flow<List<Long>> {
    val _sql: String = "SELECT DISTINCT id FROM route ORDER BY id "
    return createFlow(__db, false, arrayOf("route")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: MutableList<Long> = mutableListOf()
        while (_stmt.step()) {
          val _item: Long
          _item = _stmt.getLong(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllRoutes() {
    val _sql: String = "DELETE FROM route"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllCoords() {
    val _sql: String = "DELETE FROM coord"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteRouteById(id: Long) {
    val _sql: String = "DELETE FROM route WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteCoordByRecordNumber(recordNumberId: Long) {
    val _sql: String = "DELETE FROM coord WHERE recordNumber = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, recordNumberId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
