package com.mreigar.presentation.model

import java.io.Serializable

data class PostViewModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) : Serializable