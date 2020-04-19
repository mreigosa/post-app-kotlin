package com.mreigar.localstorage.injection

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.localstorage.database.AppDatabaseHelper
import com.mreigar.localstorage.datasource.PostDatabaseDataSourceImpl
import org.koin.dsl.module

object LocalStorageModules {

    val databaseModule = module {
        single { AppDatabaseHelper.getDatabase(get()) }

        factory<PostDatabaseDataSourceContract> { PostDatabaseDataSourceImpl() }
    }
}