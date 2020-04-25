package com.mreigar.postapp.espresso.postlist

import com.mreigar.domain.model.Post
import com.mreigar.postapp.R
import com.mreigar.presentation.mapper.PostViewModelMapper
import com.schibsted.spain.barista.assertion.BaristaListAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions

class PostListScreenRobot {

    companion object {
        infix fun postList(func: PostListScreenRobot.() -> Unit) = PostListScreenRobot()
            .apply { func() }
    }

    fun screenIsShown() {
        BaristaVisibilityAssertions.assertDisplayed(R.id.postListLayout)
    }

    fun hasCorrectContent(postList: List<Post>) {
        for (i in 0 until postList.size - 1) {
            val post = PostViewModelMapper().mapToView(postList[i])

            BaristaListAssertions.assertDisplayedAtPosition(
                listId = R.id.postRecyclerView,
                position = i,
                targetViewId = R.id.postTitle,
                text = post.title
            )

            BaristaListAssertions.assertDisplayedAtPosition(
                listId = R.id.postRecyclerView,
                position = i,
                targetViewId = R.id.postMessage,
                text = post.body
            )
        }
    }
}