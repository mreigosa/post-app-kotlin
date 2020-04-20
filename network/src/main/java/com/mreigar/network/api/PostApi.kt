package com.mreigar.network.api

import com.mreigar.network.model.CommentRemoteEntity
import com.mreigar.network.model.PostRemoteEntity
import com.mreigar.network.model.UserRemoteEntity
import retrofit2.Call
import retrofit2.http.GET

interface PostApi {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @GET("posts")
    fun getPosts(): Call<List<PostRemoteEntity>>

    @GET("users")
    fun getUsers(): Call<List<UserRemoteEntity>>

    @GET("comments")
    fun getComments(): Call<List<CommentRemoteEntity>>
}