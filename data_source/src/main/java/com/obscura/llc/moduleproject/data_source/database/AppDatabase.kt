package com.obscura.llc.moduleproject.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.obscura.llc.moduleproject.data_source.database.converters.Converters
import com.obscura.llc.moduleproject.data_source.database.dao.*
import com.obscura.llc.moduleproject.data_source.database.entity.*

@Database(entities = [(UserEntity::class), (ThemeEntity::class), (MessagesEntity::class), (LocationEntity::class), (CategoryEntity::class)], version = 7)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun themeDao(): ThemeDAO
    abstract fun messagesDao(): MessagesDao
}