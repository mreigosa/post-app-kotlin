package com.mreigar.network.mapper

import com.mreigar.data.model.UserEntity
import com.mreigar.network.model.UserRemoteEntity

class UserRemoteEntityMapper : Mapper<UserRemoteEntity, UserEntity> {

    override fun mapFromRemote(remoteEntity: UserRemoteEntity): UserEntity =
        with(remoteEntity) {
            UserEntity(id, name, username, email, website)
        }

}