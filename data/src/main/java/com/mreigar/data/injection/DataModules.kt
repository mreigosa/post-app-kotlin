package com.mreigar.data.injection

import com.mreigar.data.repository.PostRepository
import com.mreigar.domain.repository.PostRepositoryContract
import org.koin.dsl.module

object DataModules {

    val repositoryModule = module {
        factory<PostRepositoryContract> { PostRepository(get()) }
    }

}