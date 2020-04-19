package com.mreigar.data.repository

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.data.maper.CommentMapper
import com.mreigar.data.maper.PostMapper
import com.mreigar.domain.executor.Error
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
            getPostsFromLocal()
        } catch (e: Exception) {
            getPostsFromLocal()
        }

    private fun getPostsFromLocal() =
        try {
            val localResponse = databaseDataSource.getPosts()
            Success(localResponse.map { postMapper.mapFromEntity(it) })
        } catch (e: Exception) {
            Error(e)
        }

    override fun getCommentsByPostId(postId: Int): Result<List<Comment>> =
        try {
            val remoteResponse = remoteDataSource.getComments()
            databaseDataSource.saveComments(remoteResponse)
            getCommentByIdFromLocal(postId)
        } catch (e: Exception) {
            getCommentByIdFromLocal(postId)
        }

    private fun getCommentByIdFromLocal(postId: Int) =
        try {
            val localResponse = databaseDataSource.getCommentsByPostId(postId)
            Success(localResponse.map { commentMapper.mapFromEntity(it) })
        } catch (e: Exception) {
            Error(e)
        }
}