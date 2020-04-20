package com.mreigar.localstorage.database.dao

import androidx.room.*
import com.mreigar.localstorage.model.PostDatabaseEntity
import com.mreigar.localstorage.model.PostUserDatabaseEntity

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostDatabaseEntity>)

    @Query("SELECT * FROM PostDatabaseEntity")
    fun getAllPosts(): List<PostDatabaseEntity>

    @Transaction
    @Query("SELECT * FROM PostDatabaseEntity")
    fun getAllPostsAndUsers(): List<PostUserDatabaseEntity>
}