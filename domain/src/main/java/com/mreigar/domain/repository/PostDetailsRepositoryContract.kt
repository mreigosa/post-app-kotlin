package com.mreigar.domain.repository

import com.mreigar.domain.executor.Result
import com.mreigar.domain.model.EmailEmoji

interface PostDetailsRepositoryContract {
    fun getEmailEmojis(): Result<List<EmailEmoji>>
    fun getAvatarUrl(email: String): Result<String>
}