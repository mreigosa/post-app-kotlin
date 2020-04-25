package com.mreigar.localstorage.mapper

import com.mreigar.data.model.UserEntity
import com.mreigar.localstorage.model.UserDatabaseEntity

class UserDatabaseEntityMapper : Mapper<UserDatabaseEntity, UserEntity> {
    override fun mapFromDatabase(databaseEntity: UserDatabaseEntity): UserEntity =
        with(databaseEntity) {
            UserEntity(id, name, username, email)
        }

    override fun mapToDatabase(dataEntity: UserEntity): UserDatabaseEntity =
        with(dataEntity) {
            UserDatabaseEntity(id, name, username, email)
        }
}