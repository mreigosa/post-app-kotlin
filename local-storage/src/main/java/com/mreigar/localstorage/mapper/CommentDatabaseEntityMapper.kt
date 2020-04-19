package com.mreigar.localstorage.mapper

import com.mreigar.data.model.CommentEntity
import com.mreigar.localstorage.model.CommentDatabaseEntity

class CommentDatabaseEntityMapper : Mapper<CommentDatabaseEntity, CommentEntity> {
    override fun mapFromDatabase(databaseEntity: CommentDatabaseEntity): CommentEntity =
        with(databaseEntity) {
            CommentEntity(id, postId, name, email, body)
        }

    override fun mapToDatabase(dataEntity: CommentEntity): CommentDatabaseEntity = with(dataEntity) {
        CommentDatabaseEntity(id, postId, name, email, body)
    }
}