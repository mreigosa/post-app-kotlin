package com.mreigar.data.datasource

import com.mreigar.data.model.EmailEmojiEntity

interface EmojiMemoryDataSourceContract {
    fun getEmailEmojis(): List<EmailEmojiEntity>
}