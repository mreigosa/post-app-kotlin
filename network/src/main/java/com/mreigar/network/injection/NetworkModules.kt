package com.mreigar.network.injection

import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.network.api.NetworkApi
import com.mreigar.network.api.PostApi
import com.mreigar.network.datasource.PostRemoteDataSourceImpl
import org.koin.dsl.module

class NetworkModules {

    val networkModule = module {
        single { NetworkApi().provideApi(PostApi.BASE_URL, PostApi::class.java) }

        factory<PostRemoteDataSourceContract> { PostRemoteDataSourceImpl(get()) }
    }

}