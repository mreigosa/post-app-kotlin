package com.mreigar.data.repository

import com.mreigar.data.datasource.UserDatabaseDataSourceContract
import com.mreigar.data.datasource.UserRemoteDataSourceContract
import com.mreigar.data.maper.UserMapper
import com.mreigar.domain.executor.DataStatus
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

    override fun getUserById(userId: Int): Result<User> =
        try {
            val databaseResult = getUserByIdFromLocal(userId)
            if (databaseResult is Success) {
                databaseResult
            } else {
                getUserByIdFromRemote(userId)
            }
        } catch (e: Exception) {
            getUserByIdFromLocal(userId)
        }

    private fun getUserByIdFromRemote(userId: Int): Result<User> {
        val remoteResponse = remoteDataSource.getUsers()
        databaseDataSource.saveUsers(remoteResponse)
        return remoteResponse.firstOrNull { it.id == userId }?.let {
            Success(mapper.mapFromEntity(it), DataStatus.REMOTE)
        } ?: NoData
    }

    private fun getUserByIdFromLocal(userId: Int): Result<User> {
        return databaseDataSource.getUserById(userId)?.let { userEntity ->
            Success(mapper.mapFromEntity(userEntity), DataStatus.LOCAL)
        } ?: NoData
    }


}