package com.obscura.llc.moduleproject.data_source.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.obscura.llc.moduleproject.data_source.database.BaseDao
import com.obscura.llc.moduleproject.data_source.database.entity.LocationEntity

@Dao
interface LocationDao : BaseDao<LocationEntity> {

    @Query("SELECT * FROM location")
    fun query(): List<LocationEntity>

    @Query("SELECT * FROM location WHERE id = :id")
    fun queryById(id: Int): LocationEntity
}