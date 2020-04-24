package com.mreigar.data.maper

import com.mreigar.data.model.EmailEmojiEntity
import com.mreigar.domain.model.EmailEmoji

class EmailEmojiMapper : Mapper<EmailEmojiEntity, EmailEmoji> {
    override fun mapFromEntity(dataEntity: EmailEmojiEntity): EmailEmoji =
        with((dataEntity)) {
            EmailEmoji(email, emojis)
        }

    override fun mapToEntity(domainEntity: EmailEmoji): EmailEmojiEntity =
        with((domainEntity)) {
            EmailEmojiEntity(email, emojis)
        }
}