package com.mreigar.data.maper

import com.mreigar.data.model.PostEntity
import com.mreigar.domain.model.Post

class PostMapper : Mapper<PostEntity, Post> {

    override fun mapFromEntity(type: PostEntity): Post = with(type) {
        Post(userId, id, title, body)
    }

    override fun mapToEntity(type: Post): PostEntity = with(type) {
        PostEntity(userId, id, title, body)
    }
}