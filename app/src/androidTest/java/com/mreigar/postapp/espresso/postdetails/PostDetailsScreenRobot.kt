package com.mreigar.postapp.espresso.postdetails

import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.Post
import com.mreigar.domain.model.User
import com.mreigar.postapp.R
import com.mreigar.postapp.espresso.utils.EspressoTestUtils
import com.mreigar.presentation.mapper.CommentViewModelMapper
import com.mreigar.presentation.mapper.PostViewModelMapper
import com.mreigar.presentation.mapper.UserViewModelMapper
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed

class PostDetailsScreenRobot {

    companion object {
        infix fun postDetails(func: PostDetailsScreenRobot.() -> Unit) = PostDetailsScreenRobot()
            .apply { func() }
    }

    fun screenIsShown() {
        assertDisplayed(R.id.postDetailsLayout)
    }

    fun hasCorrectContent(post: Post, user: User, comments: List<Comment>) {
        val postViewModel = PostViewModelMapper().mapToView(post)
        val userViewModel = UserViewModelMapper().mapToView(user)

        assertDisplayed(R.id.postDetailsUserAvatar)
        assertDisplayed(R.id.postDetailsBody, postViewModel.body)
        assertDisplayed(R.id.postDetailsUserName, userViewModel.name)
        assertDisplayed(R.id.postDetailsUserUsername, userViewModel.username)
        assertDisplayed(R.id.postDetailsCommentsTitle, EspressoTestUtils.getText(R.string.comment_list_header, comments.size))

        comments.forEachIndexed { index, comment ->
            val commentViewModel = CommentViewModelMapper().mapToView(comment)

            assertDisplayedAtPosition(
                listId = R.id.postDetailsRecyclerView,
                position = index,
                targetViewId = R.id.itemCommentEmail,
                text = "${commentViewModel.email} ${commentViewModel.emailEmojis}"
            )

            assertDisplayedAtPosition(
                listId = R.id.postDetailsRecyclerView,
                position = index,
                targetViewId = R.id.itemCommentTitle,
                text = commentViewModel.name
            )

            assertDisplayedAtPosition(
                listId = R.id.postDetailsRecyclerView,
                position = index,
                targetViewId = R.id.itemCommentMessage,
                text = commentViewModel.body
            )
        }
    }
}