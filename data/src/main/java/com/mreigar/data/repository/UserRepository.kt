package com.mreigar.data.repository

import com.mreigar.data.datasource.UserDatabaseDataSourceContract
import com.mreigar.data.datasource.UserRemoteDataSourceContract
import com.mreigar.data.maper.UserMapper
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.User
import com.mreigar.domain.repository.UserRepositoryContract

class UserRepository(
    private val remoteDataSource: UserRemoteDataSourceContract,
    private val databaseDataSource: UserDatabaseDataSourceContract
) : UserRepositoryContract {

    private val mapper: UserMapper = UserMapper()

    override fun getUsers(): Result<List<User>> = getUsersFromRemote()

    private fun getUsersFromRemote() =
        try {
            val remoteResponse = remoteDataSource.getUsers()
            databaseDataSource.saveUsers(remoteResponse)
            Success(remoteResponse.map { mapper.mapFromEntity(it) })
        } catch (e: Exception) {
            getUsersFromLocal()
        }

    private fun getUsersFromLocal(): Result<List<User>> {
        val databaseResult = databaseDataSource.getUsers()
        return if (databaseResult.isNotEmpty()) {
            Success(databaseResult.map { mapper.mapFromEntity(it) })
        } else NoData
    }

    override fun getUserById(userId: Int): Result<User> =
        try {
            val databaseResult = getUserByIdFromLocal(userId)
            if (databaseResult is Success) {
                databaseResult
            } else {
                val remoteResponse = remoteDataSource.getUsers()
                databaseDataSource.saveUsers(remoteResponse)
                getUserByIdFromLocal(userId)
            }
        } catch (e: Exception) {
            getUserByIdFromLocal(userId)
        }

    private fun getUserByIdFromLocal(userId: Int): Result<User> {
        return databaseDataSource.getUserById(userId)?.let { userEntity ->
            Success(mapper.mapFromEntity(userEntity))
        } ?: NoData
    }

}