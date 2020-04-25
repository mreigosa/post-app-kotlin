package com.mreigar.data.datasource

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity

interface PostDatabaseDataSourceContract {
    fun getPosts(): List<PostEntity>
    fun savePosts(posts: List<PostEntity>)
    fun getCommentsByPostId(postId: Int): List<CommentEntity>
    fun saveComments(comments: List<CommentEntity>)
}