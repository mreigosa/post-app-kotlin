package com.mreigar.localstorage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mreigar.localstorage.model.UserDatabaseEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(post: UserDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(posts: List<UserDatabaseEntity>)

    @Query("SELECT * FROM UserDatabaseEntity")
    fun getAllUsers(): List<UserDatabaseEntity>

    @Query("SELECT * FROM UserDatabaseEntity where id = :userId")
    fun getById(userId: Int): UserDatabaseEntity?
}