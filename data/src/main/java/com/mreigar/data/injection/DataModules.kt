package com.mreigar.data.injection

import com.mreigar.data.repository.PostRepository
import com.mreigar.data.repository.UserRepository
import com.mreigar.domain.repository.PostRepositoryContract
import com.mreigar.domain.repository.UserRepositoryContract
import org.koin.dsl.module

object DataModules {

    val repositoryModule = module {
        factory<PostRepositoryContract> { PostRepository(get(), get()) }
        factory<UserRepositoryContract> { UserRepository(get(), get()) }
    }
}