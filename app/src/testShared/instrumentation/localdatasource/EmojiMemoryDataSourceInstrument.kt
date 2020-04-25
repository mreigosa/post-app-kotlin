package instrumentation.localdatasource

import com.mreigar.data.datasource.EmojiMemoryDataSourceContract
import com.mreigar.data.model.EmailEmojiEntity
import instrumentation.localdatasource.configuration.EmojiMemoryDataSourceConfiguration

object EmojiMemoryDataSourceInstrument {

    fun givenEmojiMemoryDataSource(
        status: DatabaseDataSourceStatus = DatabaseDataSourceStatus.SUCCESS,
        configuration: EmojiMemoryDataSourceConfiguration = EmojiMemoryDataSourceConfiguration()
    ): EmojiMemoryDataSourceContract = object : EmojiMemoryDataSourceContract {
        override fun getEmailEmojis(): List<EmailEmojiEntity> =
            when (status) {
                DatabaseDataSourceStatus.SUCCESS -> configuration.emailEmojiList
                DatabaseDataSourceStatus.NO_DATA -> listOf()
            }
    }
}