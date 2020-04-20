package com.mreigar.localstorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mreigar.localstorage.database.dao.CommentDao
import com.mreigar.localstorage.database.dao.PostDao
import com.mreigar.localstorage.database.dao.UserDao
import com.mreigar.localstorage.model.CommentDatabaseEntity
import com.mreigar.localstorage.model.PostDatabaseEntity
import com.mreigar.localstorage.model.UserDatabaseEntity

@Database(
    version = AppDatabaseHelper.DATABASE_VERSION,
    entities = [PostDatabaseEntity::class, UserDatabaseEntity::class, CommentDatabaseEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao
}