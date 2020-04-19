package com.mreigar.network.mapper

import com.mreigar.data.model.PostEntity
import com.mreigar.network.model.PostRemoteEntity

class PostRemoteEntityMapper : Mapper<PostRemoteEntity, PostEntity> {

    override fun mapFromRemote(remoteEntity: PostRemoteEntity): PostEntity =
        with(remoteEntity) {
            PostEntity(userId, id, title, body)
        }
}