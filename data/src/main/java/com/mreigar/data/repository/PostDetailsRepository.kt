package com.mreigar.data.repository

import com.mreigar.data.datasource.AvatarMemoryDataSourceContract
import com.mreigar.data.datasource.EmojiMemoryDataSourceContract
import com.mreigar.data.maper.EmailEmojiMapper
import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.EmailEmoji
import com.mreigar.domain.repository.PostDetailsRepositoryContract

class PostDetailsRepository(
    private val emojiMemoryDataSource: EmojiMemoryDataSourceContract,
    private val avatarMemoryDataSource: AvatarMemoryDataSourceContract
) : PostDetailsRepositoryContract {

    private val mapper: EmailEmojiMapper = EmailEmojiMapper()

    override fun getEmailEmojis(): Result<List<EmailEmoji>> =
        try {
            val emojis = emojiMemoryDataSource.getEmailEmojis()
            Success(emojis.map { mapper.mapFromEntity(it) }, DataStatus.LOCAL)
        } catch (e: Exception) {
            Error(e)
        }

    override fun getAvatarUrl(email: String): Result<String> =
        try {
            Success(avatarMemoryDataSource.getAvatarUrl(email), DataStatus.LOCAL)
        } catch (e: Exception) {
            Error(e)
        }
}