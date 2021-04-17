package com.mreigar.localstorage.datasource

import com.mreigar.data.datasource.UserDatabaseDataSourceContract
import com.mreigar.data.model.UserEntity
import com.mreigar.localstorage.database.AppDatabase
import com.mreigar.localstorage.mapper.UserDatabaseEntityMapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserDatabaseDataSourceImpl : UserDatabaseDataSourceContract, KoinComponent {

    private val database: AppDatabase by inject()
    private val mapper = UserDatabaseEntityMapper()

    override fun saveUsers(users: List<UserEntity>) {
        database.userDao().insertUsers(users.map { mapper.mapToDatabase(it) })
    }

    override fun getUserById(userId: Int): UserEntity? {
        return database.userDao().getById(userId)?.let { mapper.mapFromDatabase(it) }
    }
}
