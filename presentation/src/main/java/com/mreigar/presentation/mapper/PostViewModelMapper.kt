package com.mreigar.presentation.mapper

import com.mreigar.domain.model.PostUser
import com.mreigar.presentation.model.PostViewModel

class PostViewModelMapper : Mapper<PostUser, PostViewModel> {

    override fun mapToView(domainEntity: PostUser): PostViewModel = with(domainEntity) {
        PostViewModel(post.userId, post.id, post.title, post.body, user.name, user.username, user.email)
    }
}