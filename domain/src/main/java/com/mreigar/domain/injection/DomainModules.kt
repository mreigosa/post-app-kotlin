package com.mreigar.domain.injection

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.DispatcherProviderImpl
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.executor.usecase.GetPostsUseCase
import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import org.koin.dsl.module

object DomainModules {

    val useCaseModule = module {
        single<DispatcherProvider> { DispatcherProviderImpl() }

        single { GetPostsUseCase(get(), get()) }
        single { GetUserByPostUseCase(get(), get(), get()) }
        single { GetCommentsByPostUseCase(get(), get(), get()) }
    }
}