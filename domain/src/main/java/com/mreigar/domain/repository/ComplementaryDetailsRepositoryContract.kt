package com.mreigar.domain.repository

import com.mreigar.domain.executor.Result
import com.mreigar.domain.model.EmailEmoji

interface ComplementaryDetailsRepositoryContract {
    fun getEmailEmojis(): Result<List<EmailEmoji>>
}