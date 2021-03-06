package com.mreigar.localstorage.injection

import com.mreigar.data.datasource.AvatarMemoryDataSourceContract
import com.mreigar.data.datasource.EmojiMemoryDataSourceContract
import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.datasource.UserDatabaseDataSourceContract
import com.mreigar.localstorage.database.AppDatabaseHelper
import com.mreigar.localstorage.datasource.AvatarMemoryDataSourceImpl
import com.mreigar.localstorage.datasource.EmojiMemoryDataSourceImpl
import com.mreigar.localstorage.datasource.PostDatabaseDataSourceImpl
import com.mreigar.localstorage.datasource.UserDatabaseDataSourceImpl
import org.koin.dsl.module

object LocalStorageModules {

    val databaseModule = module {
        single { AppDatabaseHelper.getDatabase(get()) }

        factory<PostDatabaseDataSourceContract> { PostDatabaseDataSourceImpl() }
        factory<UserDatabaseDataSourceContract> { UserDatabaseDataSourceImpl() }
        factory<EmojiMemoryDataSourceContract> { EmojiMemoryDataSourceImpl() }
        factory<AvatarMemoryDataSourceContract> { AvatarMemoryDataSourceImpl() }
    }
}