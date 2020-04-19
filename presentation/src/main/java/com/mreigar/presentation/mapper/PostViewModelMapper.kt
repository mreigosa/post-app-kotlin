package com.mreigar.presentation.mapper

import com.mreigar.domain.model.Post
import com.mreigar.presentation.model.PostViewModel

class PostViewModelMapper : Mapper<Post, PostViewModel> {

    override fun mapToView(domainEntity: Post): PostViewModel = with(domainEntity) {
        PostViewModel(userId, id, title, body)
    }
}