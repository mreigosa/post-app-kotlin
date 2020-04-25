package com.mreigar.localstorage.mapper

import com.mreigar.data.model.CommentEntity
import com.mreigar.localstorage.model.CommentDatabaseEntity

class CommentDatabaseEntityMapper : Mapper<CommentDatabaseEntity, CommentEntity> {
    override fun mapFromDatabase(databaseEntity: CommentDatabaseEntity): CommentEntity =
        with(databaseEntity) {
            CommentEntity(id = id, postId = postId, name = name, email = email, body = body)
        }

    override fun mapToDatabase(dataEntity: CommentEntity): CommentDatabaseEntity = with(dataEntity) {
        CommentDatabaseEntity(id = id, postId = postId, name = name, email = email, body = body)
    }
}