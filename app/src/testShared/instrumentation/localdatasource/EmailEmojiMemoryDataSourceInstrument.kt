package instrumentation.localdatasource

import com.mreigar.data.datasource.EmojiMemoryDataSourceContract
import com.mreigar.data.model.EmailEmojiEntity
import instrumentation.localdatasource.configuration.EmailEmojiMemoryDataSourceConfiguration

object EmailEmojiMemoryDataSourceInstrument {

    fun givenEmailEmojiMemoryDataSource(
        status: DatabaseDataSourceStatus = DatabaseDataSourceStatus.SUCCESS,
        configuration: EmailEmojiMemoryDataSourceConfiguration = EmailEmojiMemoryDataSourceConfiguration()
    ): EmojiMemoryDataSourceContract = object : EmojiMemoryDataSourceContract {
        override fun getEmailEmojis(): List<EmailEmojiEntity> =
            when (status) {
                DatabaseDataSourceStatus.SUCCESS -> configuration.emailEmojiList
                DatabaseDataSourceStatus.NO_DATA -> listOf()
            }
    }
}