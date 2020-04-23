package com.mreigar.localstorage.datasource

import com.mreigar.data.datasource.EmojiMemoryDataSourceContract
import com.mreigar.data.model.EmailEmojiEntity
import com.mreigar.domain.model.EmailPattern

class EmojiMemoryDataSourceImpl : EmojiMemoryDataSourceContract {

    companion object {
        private const val EMOJI_INFO = "ℹ️"
        private const val EMOJI_UK = "\uD83C\uDDEC\uD83C\uDDE7"
    }

    override fun getEmailEmojis(): List<EmailEmojiEntity> =
        listOf(
            EmailEmojiEntity(EmailPattern.INFO, EMOJI_INFO),
            EmailEmojiEntity(EmailPattern.UK, EMOJI_UK)
        )
}