package com.mreigar.domain.injection

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.DispatcherProviderImpl
import com.mreigar.domain.executor.usecase.GetPostsUseCase
import org.koin.dsl.module

object DomainModules {

    val useCaseModule = module {
        single<DispatcherProvider> { DispatcherProviderImpl() }

        single { GetPostsUseCase(get()) }
    }
}