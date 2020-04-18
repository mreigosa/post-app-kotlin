package com.mreigar.presentation.mapper

import com.mreigar.domain.model.Post
import com.mreigar.presentation.model.PostViewModel

class PostViewModelMapper : Mapper<Post, PostViewModel> {

    override fun mapToView(type: Post): PostViewModel = with(type) {
        PostViewModel(userId, id, title, body)
    }
}