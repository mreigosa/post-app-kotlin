package com.mreigar.data.datasource

import com.mreigar.data.model.UserEntity

interface UserDatabaseDataSourceContract {
    fun getUsers(): List<UserEntity>
    fun saveUsers(users: List<UserEntity>)
}