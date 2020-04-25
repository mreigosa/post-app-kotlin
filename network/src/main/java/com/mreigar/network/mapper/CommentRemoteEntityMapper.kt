package com.mreigar.network.mapper

import com.mreigar.data.model.CommentEntity
import com.mreigar.network.model.CommentRemoteEntity

class CommentRemoteEntityMapper : Mapper<CommentRemoteEntity, CommentEntity> {
    override fun mapFromRemote(remoteEntity: CommentRemoteEntity): CommentEntity =
        with(remoteEntity) {
            CommentEntity(id, postId, name, email, body)
        }
}