package com.mreigar.postapp.espresso.postlist

import com.mreigar.domain.model.Post
import com.mreigar.postapp.R
import com.mreigar.postapp.espresso.utils.EspressoTestUtils
import com.mreigar.presentation.mapper.PostViewModelMapper
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem

class PostListScreenRobot {

    companion object {
        infix fun postList(func: PostListScreenRobot.() -> Unit) = PostListScreenRobot()
            .apply { func() }
    }

    fun screenIsShown() {
        assertDisplayed(R.id.postListLayout)
    }

    fun errorIsShown() {
        assertDisplayed(R.id.postErrorLayout)
        assertDisplayed(R.id.postErrorLottieView)
        assertDisplayed(R.id.postErrorTitle, EspressoTestUtils.getText(R.string.error_post_list_title))
        assertDisplayed(R.id.postErrorBody, EspressoTestUtils.getText(R.string.error_post_list_body))
        assertDisplayed(R.id.postErrorRefreshButton)
    }

    fun hasCorrectContent(postList: List<Post>) {
        for (i in 0 until postList.size - 1) {
            val post = PostViewModelMapper().mapToView(postList[i])

            assertDisplayedAtPosition(
                listId = R.id.postRecyclerView,
                position = i,
                targetViewId = R.id.postTitle,
                text = post.title
            )

            assertDisplayedAtPosition(
                listId = R.id.postRecyclerView,
                position = i,
                targetViewId = R.id.postMessage,
                text = post.body
            )
        }
    }

    fun clickPost(position: Int = 0) {
        clickListItem(R.id.postRecyclerView, position)
    }
}