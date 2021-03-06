package com.obscura.llc.moduleproject.data_source.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.obscura.llc.moduleproject.data_source.database.BaseDao
import com.obscura.llc.moduleproject.data_source.database.entity.MessagesEntity

//todo create abstract parent for DAO
@Dao
interface MessagesDao: BaseDao<MessagesEntity> {
    @Query("SELECT * FROM messages")
    fun getAll(): List<MessagesEntity>

    @Query("SELECT * FROM messages WHERE id = :id")
    fun getById(id: Int): MessagesEntity

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun insertList(listEntities: List<MessagesEntity>)

    @Update
    fun updateAll(list: List<MessagesEntity>)

    @Query("SELECT * FROM messages WHERE screenType = :screenType")
    fun getDataSource(
        screenType: String
    ): DataSource.Factory<Int, MessagesEntity>

    @Query("DELETE FROM messages WHERE screenType = :screenType AND cached = 1")
    fun deleteCachedItems(screenType : String)

    @Query("DELETE FROM messages WHERE screenType = :screenType")
    fun deleteAllItemsByType(screenType: String)

    @Transaction
    fun insertAndClearCache(
        listEntities: List<MessagesEntity>,
        screenType: String?
    ) {
        screenType?.let {
            deleteAllItemsByType(it)
        }
        insert(listEntities)
    }
}