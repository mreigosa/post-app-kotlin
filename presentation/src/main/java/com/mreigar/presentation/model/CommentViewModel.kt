package com.mreigar.presentation.model

data class CommentViewModel(
    val name: String,
    val email: String,
    val body: String,
    val emailEmojis: String,
    val avatarUrl: String
)