package com.mreigar.localstorage.mapper

import com.mreigar.data.model.PostEntity
import com.mreigar.localstorage.model.PostDatabaseEntity

class PostEntityMapper : Mapper<PostDatabaseEntity, PostEntity> {
    override fun mapFromDatabase(type: PostDatabaseEntity): PostEntity =
        with(type) {
            PostEntity(id, userId, title, body)
        }

    override fun mapToDatabase(type: PostEntity): PostDatabaseEntity =
        with(type) {
            PostDatabaseEntity(id, userId, title, body)
        }
}