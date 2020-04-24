package com.mreigar.postapp.data.mapper

import com.mreigar.data.maper.EmailEmojiMapper
import com.mreigar.domain.model.EmailEmoji
import instrumentation.data.DataEntityInstrument.givenEmailEmojiEntity
import org.assertj.core.api.Assertions
import org.junit.Test

class EmailEmojiMapperTest {

    @Test
    fun `given email emoji data entity, map to domain is correct`() {
        val dataEntity = givenEmailEmojiEntity()

        val mappedInstance: Any = EmailEmojiMapper().mapFromEntity(dataEntity)

        Assertions.assertThat(mappedInstance is EmailEmoji).isTrue()
        Assertions.assertThat((mappedInstance as EmailEmoji).email).isEqualTo(dataEntity.email)
        Assertions.assertThat(mappedInstance.emojis).isEqualTo(dataEntity.emojis)
    }
}