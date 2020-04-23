package com.mreigar.presentation.mapper

import com.mreigar.domain.model.Comment
import com.mreigar.presentation.model.CommentViewModel

class CommentViewModelMapper : Mapper<Comment, CommentViewModel> {

    override fun mapToView(domainEntity: Comment): CommentViewModel = with(domainEntity) {
        CommentViewModel(name, email, body, details?.emojis.orEmpty())
    }
}