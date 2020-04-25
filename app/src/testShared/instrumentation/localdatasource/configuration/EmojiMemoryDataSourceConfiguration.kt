package instrumentation.localdatasource.configuration

import com.mreigar.data.model.EmailEmojiEntity
import instrumentation.data.DataEntityInstrument.givenEmailEmojiEntity

data class EmojiMemoryDataSourceConfiguration(
    var emailEmojiList: List<EmailEmojiEntity> = listOf(givenEmailEmojiEntity())
)