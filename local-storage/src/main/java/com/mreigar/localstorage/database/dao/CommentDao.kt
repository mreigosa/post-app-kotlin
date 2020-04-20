package com.mreigar.localstorage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mreigar.localstorage.model.CommentDatabaseEntity

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: CommentDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<CommentDatabaseEntity>)

    @Query("SELECT * FROM CommentDatabaseEntity WHERE post_id = :postId")
    fun getCommentsByPostId(postId: Int): List<CommentDatabaseEntity>

    @Query("SELECT * FROM CommentDatabaseEntity")
    fun getAll(): List<CommentDatabaseEntity>
}