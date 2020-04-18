package com.mreigar.network

import retrofit2.Call

@Throws(Exception::class)
fun <T> Call<T>.executeCall(): T? = execute().run {
    if (isSuccessful) {
        body()
    } else {
        throw Exception(errorBody()?.string())
    }
}