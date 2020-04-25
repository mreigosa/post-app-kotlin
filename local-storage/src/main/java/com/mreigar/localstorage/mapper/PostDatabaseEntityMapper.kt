package com.mreigar.localstorage.mapper

import com.mreigar.data.model.PostEntity
import com.mreigar.localstorage.model.PostDatabaseEntity

class PostDatabaseEntityMapper : Mapper<PostDatabaseEntity, PostEntity> {
    override fun mapFromDatabase(databaseEntity: PostDatabaseEntity): PostEntity =
        with(databaseEntity) {
            PostEntity(id = id, userId = userId, title = title, body = body)
        }

    override fun mapToDatabase(dataEntity: PostEntity): PostDatabaseEntity =
        with(dataEntity) {
            PostDatabaseEntity(id = id, userId = userId, title = title, body = body)
        }
}