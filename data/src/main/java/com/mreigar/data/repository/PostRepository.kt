package com.mreigar.data.repository

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.data.maper.PostMapper
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.Post
import com.mreigar.domain.repository.PostRepositoryContract

class PostRepository(
    private val remoteDataSource: PostRemoteDataSourceContract,
    private val databaseDataSource: PostDatabaseDataSourceContract
) : PostRepositoryContract {

    private val mapper: PostMapper = PostMapper()

    override fun getPosts(): Result<List<Post>> =
        try {
            val remoteResponse = remoteDataSource.getPosts()
            databaseDataSource.savePosts(remoteResponse)
            getPostsFromLocal()
        } catch (e: Exception) {
            getPostsFromLocal()
        }

    private fun getPostsFromLocal() =
        try {
            val localResponse = databaseDataSource.getPosts()
            Success(localResponse.map { mapper.mapFromEntity(it) })
        } catch (e: Exception) {
            Error(e)
        }
}