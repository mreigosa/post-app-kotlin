package com.mreigar.presentation.viewmodel

import com.mreigar.presentation.model.PostViewModel

sealed class PostListViewState {
    object Loading : PostListViewState()
    object Error : PostListViewState()
    data class Success(val posts: List<PostViewModel>): PostListViewState()
}
