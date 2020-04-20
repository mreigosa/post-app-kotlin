package com.mreigar.domain.injection

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.DispatcherProviderImpl
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import com.mreigar.domain.executor.usecase.GetPostsUsersUseCase
import org.koin.dsl.module

object DomainModules {

    val useCaseModule = module {
        single<DispatcherProvider> { DispatcherProviderImpl() }

        single { GetUserByPostUseCase(get(), get()) }
        single { GetCommentsByPostUseCase(get(), get()) }
        single { GetPostsUsersUseCase(get(), get(), get()) }
    }
}