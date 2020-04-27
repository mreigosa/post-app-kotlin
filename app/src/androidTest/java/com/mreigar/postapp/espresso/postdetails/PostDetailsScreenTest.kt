package com.mreigar.postapp.espresso.postdetails

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.mreigar.data.maper.CommentMapper
import com.mreigar.data.maper.UserMapper
import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.UserEntity
import com.mreigar.postapp.espresso.BaseScreenTest
import com.mreigar.postapp.espresso.postdetails.PostDetailsScreenRobot.Companion.postDetails
import com.mreigar.postapp.postdetails.PostDetailsActivity
import com.mreigar.presentation.mapper.PostViewModelMapper
import com.mreigar.presentation.model.PostViewModel
import instrumentation.data.DataEntityInstrument.givenCommentEntity
import instrumentation.data.DataEntityInstrument.givenUserEntity
import instrumentation.domain.DomainEntityInstrument.givenPost
import instrumentation.localdatasource.DatabaseDataSourceStatus
import instrumentation.presentation.PresentationEntityInstrument.givenPostViewModel
import instrumentation.remotedatasource.RemoteDataSourceStatus
import org.junit.Rule
import org.junit.Test

class PostDetailsScreenTest : BaseScreenTest() {

    @get: Rule
    val activityRule = object : ActivityTestRule<PostDetailsActivity>(PostDetailsActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            injectMockModules()
        }
    }

    private fun initState(
        post: PostViewModel,
        users: List<UserEntity>,
        comments: List<CommentEntity>
    ) {
        userRemoteDataSourceConfiguration.userEntityList = users
        postRemoteDataSourceConfiguration.commentEntityList = comments

        val intent = Intent().apply {
            putExtra(PostDetailsActivity.PARAMS_ARG, post)
        }
        activityRule.launchActivity(intent)
    }

    @Test
    fun given_post_details_screen_has_correct_content() {
        val post = givenPost(userId = 5, id = 1)

        val user = givenUserEntity(userId = 5)
        val comments = listOf(givenCommentEntity(postId = 1, id = 1), givenCommentEntity(postId = 1, id = 2))

        initState(
            post = PostViewModelMapper().mapToView(post),
            users = listOf(user),
            comments = comments
        )

        postDetails {
            hasCorrectContent(
                post = post,
                user = UserMapper().mapFromEntity(user),
                comments = comments.map { CommentMapper().mapFromEntity(it) }
            )
        }
    }

    @Test
    fun given_post_details_screen_when_no_comments_retrieved_has_correct_content() {
        val post = givenPost(userId = 5, id = 1)
        val user = givenUserEntity(userId = 5)

        initState(
            post = PostViewModelMapper().mapToView(post),
            users = listOf(user),
            comments = listOf()
        )

        postDetails {
            hasCorrectContent(
                post = post,
                user = UserMapper().mapFromEntity(user),
                comments = listOf()
            )
        }
    }

    @Test
    fun given_post_list_activity_when_content_not_received_empty_screen_is_shown() {
        userRemoteDataSourceStatus = RemoteDataSourceStatus.ERROR
        userDatabaseDataSourceStatus = DatabaseDataSourceStatus.NO_DATA
        initState(givenPostViewModel(), listOf(), listOf())

        postDetails {
            screenIsShown()
            errorIsShown()
        }
    }
}