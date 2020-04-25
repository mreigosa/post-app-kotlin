package com.mreigar.network.datasource

import com.mreigar.data.datasource.UserRemoteDataSourceContract
import com.mreigar.data.model.UserEntity
import com.mreigar.network.api.PostApi
import com.mreigar.network.executeCall
import com.mreigar.network.mapper.UserRemoteEntityMapper

class UserRemoteDataSourceImpl(private val api: PostApi) : UserRemoteDataSourceContract {

    private val mapper = UserRemoteEntityMapper()

    override fun getUsers(): List<UserEntity> {
        val response = api.getUsers().executeCall()
        return response?.let {
            it.map { userRemoteEntity -> mapper.mapFromRemote(userRemoteEntity) }
        } ?: throw Exception()
    }
}