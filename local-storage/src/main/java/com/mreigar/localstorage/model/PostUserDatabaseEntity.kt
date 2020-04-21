package com.mreigar.localstorage.model

import androidx.room.Embedded
import androidx.room.Relation

data class PostUserDatabaseEntity(
    @Embedded val user: UserDatabaseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val post: PostDatabaseEntity
)