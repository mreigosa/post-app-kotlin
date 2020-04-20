package com.mreigar.data.datasource

import com.mreigar.data.model.UserEntity

interface UserRemoteDataSourceContract {
    fun getUsers(): List<UserEntity>
}