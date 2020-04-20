package com.mreigar.data.datasource

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import com.mreigar.data.model.PostUserEntity

interface PostDatabaseDataSourceContract {
    fun getPosts(): List<PostEntity>
    fun savePosts(posts: List<PostEntity>)
    fun getPostsUsers(): List<PostUserEntity>
    fun getCommentsByPostId(postId: Int): List<CommentEntity>
    fun saveComments(comments: List<CommentEntity>)
}