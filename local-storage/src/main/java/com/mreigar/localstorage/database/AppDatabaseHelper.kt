package com.mreigar.localstorage.database

import android.content.Context
import androidx.room.Room

object AppDatabaseHelper {

    const val DATABASE_NAME = "post_database"
    const val DATABASE_VERSION = 1

    fun getDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DATABASE_NAME
    ).build()
}