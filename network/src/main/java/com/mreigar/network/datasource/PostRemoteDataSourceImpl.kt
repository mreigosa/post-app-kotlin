package com.mreigar.network.datasource

import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import com.mreigar.network.api.PostApi
import com.mreigar.network.executeCall
import com.mreigar.network.mapper.CommentRemoteEntityMapper
import com.mreigar.network.mapper.PostRemoteEntityMapper

class PostRemoteDataSourceImpl(private val api: PostApi) : PostRemoteDataSourceContract {

    private val postMapper = PostRemoteEntityMapper()
    private val commentMapper = CommentRemoteEntityMapper()

    override fun getPosts(): List<PostEntity> {
        val response = api.getPosts().executeCall()
        return response?.let {
            it.map { postRemoteEntity -> postMapper.mapFromRemote(postRemoteEntity) }
        } ?: throw Exception()
    }

    override fun getComments(): List<CommentEntity> {
        val response = api.getComments().executeCall()
        return response?.let {
            it.map { commentRemoteEntity -> commentMapper.mapFromRemote(commentRemoteEntity) }
        } ?: throw Exception()
    }
}