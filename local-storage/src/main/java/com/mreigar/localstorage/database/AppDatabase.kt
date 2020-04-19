package com.mreigar.localstorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mreigar.localstorage.database.dao.PostDao
import com.mreigar.localstorage.model.PostDatabaseEntity

@Database(
    version = AppDatabaseHelper.DATABASE_VERSION,
    entities = [PostDatabaseEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}