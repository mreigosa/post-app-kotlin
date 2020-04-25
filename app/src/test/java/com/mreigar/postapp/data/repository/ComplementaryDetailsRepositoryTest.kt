package com.mreigar.postapp.data.repository

import com.mreigar.data.repository.PostDetailsRepository
import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.Success
import instrumentation.localdatasource.EmailEmojiMemoryDataSourceInstrument.givenEmailEmojiMemoryDataSource
import org.assertj.core.api.Assertions
import org.junit.Test

class ComplementaryDetailsRepositoryTest {

    @Test
    fun `given repository, email emojis are retrieved with success result`() {
        val repository = PostDetailsRepository(
            givenEmailEmojiMemoryDataSource()
        )

        val result = repository.getEmailEmojis()

        Assertions.assertThat(result is Success).isTrue()
        Assertions.assertThat((result).dataStatus == DataStatus.LOCAL).isTrue()
        Assertions.assertThat((result as Success).data).isNotNull()
        Assertions.assertThat(result.data).isNotEmpty()
    }
}