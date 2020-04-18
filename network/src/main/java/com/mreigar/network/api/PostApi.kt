package com.mreigar.network.api

import com.mreigar.network.model.PostRemoteEntity
import retrofit2.Call
import retrofit2.http.GET

interface PostApi {

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @GET("posts")
    fun getPosts(): Call<List<PostRemoteEntity>>
}