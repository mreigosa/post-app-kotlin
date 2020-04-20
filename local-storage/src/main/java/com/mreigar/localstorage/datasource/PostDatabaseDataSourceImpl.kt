package com.mreigar.localstorage.datasource

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import com.mreigar.data.model.PostUserEntity
import com.mreigar.localstorage.database.AppDatabase
import com.mreigar.localstorage.mapper.CommentDatabaseEntityMapper
import com.mreigar.localstorage.mapper.PostDatabaseEntityMapper
import com.mreigar.localstorage.mapper.UserDatabaseEntityMapper
import org.koin.core.KoinComponent
import org.koin.core.inject

class PostDatabaseDataSourceImpl : PostDatabaseDataSourceContract, KoinComponent {

    private val database: AppDatabase by inject()
    private val postMapper = PostDatabaseEntityMapper()
    private val commentMapper = CommentDatabaseEntityMapper()
    private val userMapper = UserDatabaseEntityMapper()

    override fun getPosts(): List<PostEntity> {
        val data = database.postDao().getAllPosts()
        return data.map { postMapper.mapFromDatabase(it) }
    }

    override fun savePosts(posts: List<PostEntity>) {
        database.postDao().insertPosts(posts.map { postMapper.mapToDatabase(it) })
    }

    override fun getPostsUsers(): List<PostUserEntity> {
        val data = database.postDao().getAllPostsAndUsers()
        return data.map { postUserDatabaseEntity ->
            PostUserEntity(
                postMapper.mapFromDatabase(postUserDatabaseEntity.post),
                userMapper.mapFromDatabase(postUserDatabaseEntity.user)
            )
        }
    }

    override fun getCommentsByPostId(postId: Int): List<CommentEntity> {
        val data = database.commentDao().getCommentsByPostId(postId)
        return data.map { commentMapper.mapFromDatabase(it) }
    }

    override fun saveComments(comments: List<CommentEntity>) {
        database.commentDao().insertComments(comments.map { commentMapper.mapToDatabase(it) })
    }
}