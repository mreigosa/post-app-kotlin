package com.mreigar.data.maper

import com.mreigar.data.model.CommentEntity
import com.mreigar.domain.model.Comment

class CommentMapper : Mapper<CommentEntity, Comment> {
    override fun mapFromEntity(dataEntity: CommentEntity): Comment = with(dataEntity) {
        Comment(id, postId, name, email, body)
    }

    override fun mapToEntity(domainEntity: Comment): CommentEntity = with(domainEntity) {
        CommentEntity(id, postId, name, email, body)
    }
}