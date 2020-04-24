package com.mreigar.data.repository

import com.mreigar.data.datasource.EmojiMemoryDataSourceContract
import com.mreigar.data.maper.EmailEmojiMapper
import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.EmailEmoji
import com.mreigar.domain.repository.ComplementaryDetailsRepositoryContract

class ComplementaryDetailsRepository(
    private val emojiMemoryDataSource: EmojiMemoryDataSourceContract
) : ComplementaryDetailsRepositoryContract {

    private val mapper: EmailEmojiMapper = EmailEmojiMapper()

    override fun getEmailEmojis(): Result<List<EmailEmoji>> =
        try {
            val emojis = emojiMemoryDataSource.getEmailEmojis()
            Success(emojis.map { mapper.mapFromEntity(it) }, DataStatus.LOCAL)
        } catch (e: Exception) {
            Error(e)
        }
}