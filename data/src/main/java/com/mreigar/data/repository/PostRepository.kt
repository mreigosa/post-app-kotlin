package com.mreigar.data.repository

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.data.maper.CommentMapper
import com.mreigar.data.maper.PostMapper
import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.Post
import com.mreigar.domain.repository.PostRepositoryContract

class PostRepository(
    private val remoteDataSource: PostRemoteDataSourceContract,
    private val databaseDataSource: PostDatabaseDataSourceContract
) : PostRepositoryContract {

    private val postMapper: PostMapper = PostMapper()
    private val commentMapper: CommentMapper = CommentMapper()

    override fun getPosts(): Result<List<Post>> =
        try {
            val remoteResponse = remoteDataSource.getPosts()
            databaseDataSource.savePosts(remoteResponse)
            Success(remoteResponse.map { postMapper.mapFromEntity(it) }, DataStatus.REMOTE)
        } catch (e: Exception) {
            getPostsFromLocal()
        }

    private fun getPostsFromLocal(): Result<List<Post>> {
        val localResponse = databaseDataSource.getPosts()
        return if (localResponse.isEmpty()) NoData else Success(localResponse.map { postMapper.mapFromEntity(it) }, DataStatus.LOCAL)
    }

    override fun getCommentsByPostId(postId: Int): Result<List<Comment>> =
        try {
            val remoteResponse = remoteDataSource.getComments()
            databaseDataSource.saveComments(remoteResponse)
            val commentsById = remoteResponse.filter { it.id == postId }
            if (commentsById.isEmpty()) NoData else Success(commentsById.map { commentMapper.mapFromEntity(it) }, DataStatus.REMOTE)
        } catch (e: Exception) {
            getCommentByIdFromLocal(postId)
        }

    private fun getCommentByIdFromLocal(postId: Int): Result<List<Comment>> {
        val localResponse = databaseDataSource.getCommentsByPostId(postId)
        return if (localResponse.isEmpty()) NoData else Success(localResponse.map { commentMapper.mapFromEntity(it) }, DataStatus.LOCAL)
    }
}