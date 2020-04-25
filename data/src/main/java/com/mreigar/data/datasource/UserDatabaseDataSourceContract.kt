package com.mreigar.data.datasource

import com.mreigar.data.model.UserEntity

interface UserDatabaseDataSourceContract {
    fun saveUsers(users: List<UserEntity>)
    fun getUserById(userId: Int): UserEntity?
}