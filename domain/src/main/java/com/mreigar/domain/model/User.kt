package com.mreigar.domain.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val avatarUrl: String?
)