package com.mreigar.network.api

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkApi : KoinComponent {

    companion object {
        private const val OK_HTTP_TIMEOUT = 30L
    }

    fun <T> provideApi(
        baseUrl: String,
        clazz: Class<T>,
        interceptors: List<Interceptor> = listOf(),
        authenticator: Authenticator? = null
    ): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideOkHttpClient(interceptors, authenticator))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(clazz)

    private fun provideOkHttpClient(interceptors: List<Interceptor>, authenticator: Authenticator?): OkHttpClient =
        OkHttpClient.Builder().apply {

            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

            for (interceptor in interceptors) {
                addInterceptor(interceptor)
            }

            authenticator?.let {
                authenticator(it)
            }

            connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        }.build()

}