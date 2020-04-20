package com.mreigar.data.maper

import com.mreigar.data.model.PostEntity
import com.mreigar.domain.model.Post

class PostMapper : Mapper<PostEntity, Post> {

    override fun mapFromEntity(dataEntity: PostEntity): Post = with(dataEntity) {
        Post(userId, id, title, body)
    }

    override fun mapToEntity(domainEntity: Post): PostEntity = with(domainEntity) {
        PostEntity(userId, id, title, body)
    }
}