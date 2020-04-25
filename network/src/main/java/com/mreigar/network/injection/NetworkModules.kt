package com.mreigar.network.injection

import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.data.datasource.UserRemoteDataSourceContract
import com.mreigar.network.api.NetworkApi
import com.mreigar.network.api.PostApi
import com.mreigar.network.datasource.PostRemoteDataSourceImpl
import com.mreigar.network.datasource.UserRemoteDataSourceImpl
import org.koin.dsl.module

object NetworkModules {

    val networkModule = module {
        single { NetworkApi().provideApi(PostApi.BASE_URL, PostApi::class.java) }

        factory<PostRemoteDataSourceContract> { PostRemoteDataSourceImpl(get()) }
        factory<UserRemoteDataSourceContract> { UserRemoteDataSourceImpl(get()) }
    }
}