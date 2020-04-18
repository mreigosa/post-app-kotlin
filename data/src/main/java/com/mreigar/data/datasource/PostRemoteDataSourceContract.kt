package com.mreigar.data.datasource

import com.mreigar.data.model.PostEntity

interface PostRemoteDataSourceContract {
    fun getPosts(): List<PostEntity>
}