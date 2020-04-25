package com.mreigar.data.maper

import com.mreigar.data.model.UserEntity
import com.mreigar.domain.model.User

class UserMapper : Mapper<UserEntity, User> {
    override fun mapFromEntity(dataEntity: UserEntity): User = with(dataEntity) {
        User(id, name, username, email, null)
    }

    override fun mapToEntity(domainEntity: User): UserEntity = with(domainEntity) {
        UserEntity(id, name, username, email)
    }
}