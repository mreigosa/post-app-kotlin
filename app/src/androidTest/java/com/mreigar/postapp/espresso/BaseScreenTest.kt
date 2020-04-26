package com.mreigar.postapp.espresso

import com.mreigar.postapp.espresso.utils.PostAppTestRunner
import instrumentation.localdatasource.AvatarMemoryDataSourceInstrument.givenAvatarMemoryDataSource
import instrumentation.localdatasource.DatabaseDataSourceStatus
import instrumentation.localdatasource.EmojiMemoryDataSourceInstrument.givenEmojiMemoryDataSource
import instrumentation.localdatasource.PostDatabaseDataSourceInstrument.givenPostDatabaseDataSource
import instrumentation.localdatasource.UserDatabaseDataSourceInstrument.givenUserDatabaseDataSource
import instrumentation.localdatasource.configuration.AvatarMemoryDataSourceConfiguration
import instrumentation.localdatasource.configuration.EmojiMemoryDataSourceConfiguration
import instrumentation.localdatasource.configuration.PostDatabaseDataSourceConfiguration
import instrumentation.localdatasource.configuration.UserDatabaseDataSourceConfiguration
import instrumentation.remotedatasource.PostRemoteDataSourceInstrument.givenPostRemoteDataSource
import instrumentation.remotedatasource.RemoteDataSourceStatus
import instrumentation.remotedatasource.UserRemoteDataSourceInstrument.givenUserRemoteDataSource
import instrumentation.remotedatasource.configuration.PostRemoteDataSourceConfiguration
import instrumentation.remotedatasource.configuration.UserRemoteDataSourceConfiguration
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

open class BaseScreenTest : PostAppTestRunner() {

    protected open var customMockModules: Module = module {}

    protected var postRemoteDataSourceConfiguration = PostRemoteDataSourceConfiguration()
    protected var postDatabaseDataSourceConfiguration = PostDatabaseDataSourceConfiguration()
    protected var postRemoteDataSourceStatus = RemoteDataSourceStatus.SUCCESS
    protected var postDatabaseDataSourceStatus = DatabaseDataSourceStatus.SUCCESS

    protected var userRemoteDataSourceConfiguration = UserRemoteDataSourceConfiguration()
    protected var userDatabaseDataSourceConfiguration = UserDatabaseDataSourceConfiguration()

    protected var emojiMemoryDataSourceConfiguration = EmojiMemoryDataSourceConfiguration()
    protected var avatarMemoryDataSourceConfiguration = AvatarMemoryDataSourceConfiguration()

    fun injectMockModules() {
        val mockModules = module {
            single(override = true) { givenPostRemoteDataSource(configuration = postRemoteDataSourceConfiguration, status = postRemoteDataSourceStatus) }
            single(override = true) { givenPostDatabaseDataSource(configuration = postDatabaseDataSourceConfiguration, status = postDatabaseDataSourceStatus) }
            single(override = true) { givenUserRemoteDataSource(configuration = userRemoteDataSourceConfiguration) }
            single(override = true) { givenUserDatabaseDataSource(configuration = userDatabaseDataSourceConfiguration) }
            single(override = true) { givenEmojiMemoryDataSource(configuration = emojiMemoryDataSourceConfiguration) }
            single(override = true) { givenAvatarMemoryDataSource(configuration = avatarMemoryDataSourceConfiguration) }
        }

        loadKoinModules(listOf(mockModules, customMockModules))
    }
}