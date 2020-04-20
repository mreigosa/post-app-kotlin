package com.mreigar.localstorage.model

import androidx.room.Embedded
import androidx.room.Relation

data class PostUserDatabaseEntity(
    @Embedded val post: PostDatabaseEntity,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id"
    )
    val user: UserDatabaseEntity
)