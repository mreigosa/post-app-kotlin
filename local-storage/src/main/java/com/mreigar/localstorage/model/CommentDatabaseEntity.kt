package com.mreigar.localstorage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentDatabaseEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "post_id") val postId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "body") val body: String
)