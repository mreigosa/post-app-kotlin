package com.mreigar.localstorage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mreigar.localstorage.model.PostDatabaseEntity

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostDatabaseEntity>)

    @Query("SELECT * FROM PostDatabaseEntity")
    fun getAllPosts(): List<PostDatabaseEntity>
}