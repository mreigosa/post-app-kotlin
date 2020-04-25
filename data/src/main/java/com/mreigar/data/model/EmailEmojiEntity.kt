package com.mreigar.data.model

import com.mreigar.domain.model.EmailPattern

data class EmailEmojiEntity(
    val email: EmailPattern,
    val emojis: String
)