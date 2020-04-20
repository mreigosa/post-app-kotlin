package com.mreigar.domain.repository

import com.mreigar.domain.executor.Result
import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.Post
import com.mreigar.domain.model.PostUser

interface PostRepositoryContract {
    fun getPosts(): Result<List<Post>>
    fun getPostsUsers(): Result<List<PostUser>>
    fun getCommentsByPostId(postId: Int): Result<List<Comment>>
}