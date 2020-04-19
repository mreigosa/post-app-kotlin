package com.mreigar.localstorage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDatabaseEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "website") val website: String
)