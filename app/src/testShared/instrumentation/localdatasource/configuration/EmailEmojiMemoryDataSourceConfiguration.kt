package instrumentation.localdatasource.configuration

import com.mreigar.data.model.EmailEmojiEntity
import instrumentation.data.DataEntityInstrument.givenEmailEmojiEntity

data class EmailEmojiMemoryDataSourceConfiguration(
    var emailEmojiList: List<EmailEmojiEntity> = listOf(givenEmailEmojiEntity())
)