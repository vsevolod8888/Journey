package com.sever.journey.`data`.room

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class Database_Impl : Database() {
  private val _coordDao: Lazy<CoordDao> = lazy {
    CoordDao_Impl(this)
  }

  public override val dao: CoordDao
    get() = _coordDao.value

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(2, "092fe076c2b2e577bf598e6f73353e35", "08ded6568dbc03d59b2a0dbe514d88e2") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `coord` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `checktime` INTEGER NOT NULL, `recordNumber` INTEGER NOT NULL, `lattitude` REAL NOT NULL, `longittude` REAL NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `route` (`id` INTEGER NOT NULL, `epochDays` INTEGER NOT NULL, `isDrawing` INTEGER NOT NULL, `lenght` TEXT NOT NULL, `checktime` INTEGER NOT NULL, `recordRouteName` TEXT NOT NULL, `isClicked` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '092fe076c2b2e577bf598e6f73353e35')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `coord`")
        connection.execSQL("DROP TABLE IF EXISTS `route`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsCoord: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCoord.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCoord.put("checktime", TableInfo.Column("checktime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCoord.put("recordNumber", TableInfo.Column("recordNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCoord.put("lattitude", TableInfo.Column("lattitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCoord.put("longittude", TableInfo.Column("longittude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCoord: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCoord: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCoord: TableInfo = TableInfo("coord", _columnsCoord, _foreignKeysCoord, _indicesCoord)
        val _existingCoord: TableInfo = read(connection, "coord")
        if (!_infoCoord.equals(_existingCoord)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |coord(com.sever.journey.data.room.CoordinatesEntity).
              | Expected:
              |""".trimMargin() + _infoCoord + """
              |
              | Found:
              |""".trimMargin() + _existingCoord)
        }
        val _columnsRoute: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRoute.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRoute.put("epochDays", TableInfo.Column("epochDays", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRoute.put("isDrawing", TableInfo.Column("isDrawing", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRoute.put("lenght", TableInfo.Column("lenght", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRoute.put("checktime", TableInfo.Column("checktime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRoute.put("recordRouteName", TableInfo.Column("recordRouteName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRoute.put("isClicked", TableInfo.Column("isClicked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRoute: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRoute: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRoute: TableInfo = TableInfo("route", _columnsRoute, _foreignKeysRoute, _indicesRoute)
        val _existingRoute: TableInfo = read(connection, "route")
        if (!_infoRoute.equals(_existingRoute)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |route(com.sever.journey.data.room.RouteEntity).
              | Expected:
              |""".trimMargin() + _infoRoute + """
              |
              | Found:
              |""".trimMargin() + _existingRoute)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "coord", "route")
  }

  public override fun clearAllTables() {
    super.performClear(false, "coord", "route")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(CoordDao::class, CoordDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }
}
