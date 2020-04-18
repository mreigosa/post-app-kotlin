package com.mreigar.network.datasource

import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.data.model.PostEntity
import com.mreigar.network.api.PostApi
import com.mreigar.network.executeCall
import com.mreigar.network.mapper.PostEntityMapper

class PostRemoteDataSourceImpl(private val api: PostApi) : PostRemoteDataSourceContract {

    private val mapper = PostEntityMapper()

    override fun getPosts(): List<PostEntity> {
        val response = api.getPosts().executeCall()
        return response?.let {
            it.map { postRemoteEntity -> mapper.mapFromRemote(postRemoteEntity) }
        } ?: throw Exception()
    }

}