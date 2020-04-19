package com.mreigar.postapp.espresso.postlist

import androidx.test.rule.ActivityTestRule
import com.mreigar.data.maper.PostMapper
import com.mreigar.postapp.espresso.postlist.PostListScreenRobot.Companion.postList
import com.mreigar.postapp.espresso.utils.PostAppTestRunner
import com.mreigar.postapp.postlist.PostListActivity
import instrumentation.remotedatasource.PostRemoteDataSourceConfiguration
import instrumentation.remotedatasource.PostRemoteDataSourceInstrument
import instrumentation.remotedatasource.givenPostEntityList
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class PostListScreenTest : PostAppTestRunner() {

    private var remoteDataSourceConfiguration = PostRemoteDataSourceConfiguration()

    @get: Rule
    val activityRule = object : ActivityTestRule<PostListActivity>(PostListActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            injectMockModules()
        }
    }

    private fun injectMockModules() {
        val mockModules = module {
            single(override = true) {
                PostRemoteDataSourceInstrument.givenPostRemoteDataSource(configuration = remoteDataSourceConfiguration)
            }
        }

        loadKoinModules(listOf(mockModules))
    }

    private fun launchScreen() {
        activityRule.launchActivity(null)
    }

    @Test
    fun given_post_list_activity_when_content_is_received_list_is_shown() {
        val posts = givenPostEntityList(5)
        remoteDataSourceConfiguration.postEntityList = posts

        launchScreen()

        postList {
            screenIsShown()
            hasCorrectContent(posts.map { PostMapper().mapFromEntity(it) })
        }
    }
}