package com.mreigar.presentation.viewmodel

import com.mreigar.presentation.model.CommentViewModel
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.model.UserViewModel

sealed class PostDetailsViewState {
    object Loading : PostDetailsViewState()
    object Error : PostDetailsViewState()
    data class UserRetrieved(
            val post: PostViewModel,
            val user: UserViewModel
    ) : PostDetailsViewState()
}

sealed class PostCommentsViewState {
    object Loading : PostCommentsViewState()
    object Error : PostCommentsViewState()
    data class CommentsRetrieved(
            val comments: List<CommentViewModel>
    ) : PostCommentsViewState()
}
