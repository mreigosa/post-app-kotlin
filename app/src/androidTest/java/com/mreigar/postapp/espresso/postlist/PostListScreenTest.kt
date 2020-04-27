package com.mreigar.postapp.espresso.postlist

import androidx.test.rule.ActivityTestRule
import com.mreigar.data.maper.PostMapper
import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import com.mreigar.data.model.UserEntity
import com.mreigar.postapp.espresso.BaseScreenTest
import com.mreigar.postapp.espresso.postdetails.PostDetailsScreenRobot.Companion.postDetails
import com.mreigar.postapp.espresso.postlist.PostListScreenRobot.Companion.postList
import com.mreigar.postapp.postlist.PostListActivity
import instrumentation.data.DataEntityInstrument.givenCommentEntityList
import instrumentation.data.DataEntityInstrument.givenPostEntityList
import instrumentation.data.DataEntityInstrument.givenUserEntity
import instrumentation.localdatasource.DatabaseDataSourceStatus
import instrumentation.localdatasource.configuration.PostDatabaseDataSourceConfiguration
import instrumentation.localdatasource.configuration.UserDatabaseDataSourceConfiguration
import instrumentation.remotedatasource.RemoteDataSourceStatus
import instrumentation.remotedatasource.configuration.PostRemoteDataSourceConfiguration
import org.junit.Rule
import org.junit.Test

class PostListScreenTest : BaseScreenTest() {

    @get: Rule
    val activityRule = object : ActivityTestRule<PostListActivity>(PostListActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            injectMockModules()
        }
    }

    private fun initState(posts: List<PostEntity>, user: UserEntity? = null, comments: List<CommentEntity> = listOf()) {
        postRemoteDataSourceConfiguration = PostRemoteDataSourceConfiguration(
            postEntityList = posts,
            commentEntityList = comments
        )
        postDatabaseDataSourceConfiguration = PostDatabaseDataSourceConfiguration(
            postEntityList = posts
        )
        userDatabaseDataSourceConfiguration = UserDatabaseDataSourceConfiguration(
            userEntity = user
        )
        activityRule.launchActivity(null)
    }

    @Test
    fun given_post_list_activity_when_content_is_received_list_is_shown() {
        val posts = givenPostEntityList(5)
        initState(posts)

        postList {
            screenIsShown()
            hasCorrectContent(posts.map { PostMapper().mapFromEntity(it) })
        }
    }

    @Test
    fun given_post_list_activity_when_post_is_clicked_details_screen_is_shown() {
        initState(givenPostEntityList(5), givenUserEntity(userId = 1), givenCommentEntityList(size = 5, postId = 1))

        postList {
            screenIsShown()
            clickPost(1)
        }

        postDetails {
            screenIsShown()
        }
    }

    @Test
    fun given_post_list_activity_when_content_not_received_empty_screen_is_shown() {
        postRemoteDataSourceStatus = RemoteDataSourceStatus.ERROR
        postDatabaseDataSourceStatus = DatabaseDataSourceStatus.NO_DATA
        initState(listOf())

        postList {
            screenIsShown()
            errorIsShown()
        }
    }
}