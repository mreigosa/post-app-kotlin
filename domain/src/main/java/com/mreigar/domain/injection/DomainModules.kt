package com.mreigar.domain.injection

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.DispatcherProviderImpl
import com.mreigar.domain.executor.Invoker
import com.mreigar.domain.executor.UseCaseInvoker
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.executor.usecase.GetPostsUseCase
import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import org.koin.dsl.module

object DomainModules {

    val useCaseModule = module {
        factory<Invoker> { UseCaseInvoker(get()) }
        single<DispatcherProvider> { DispatcherProviderImpl() }

        factory { GetPostsUseCase(get()) }
        factory { GetUserByPostUseCase(get(), get()) }
        factory { GetCommentsByPostUseCase(get(), get()) }
    }
}