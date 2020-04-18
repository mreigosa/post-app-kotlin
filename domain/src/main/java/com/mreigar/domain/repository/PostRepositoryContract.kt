package com.mreigar.domain.repository

import com.mreigar.domain.executor.Result
import com.mreigar.domain.model.Post

interface PostRepositoryContract {
    fun getPosts(): Result<List<Post>>
}