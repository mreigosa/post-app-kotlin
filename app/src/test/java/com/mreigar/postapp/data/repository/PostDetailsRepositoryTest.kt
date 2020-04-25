package com.mreigar.postapp.data.repository

import com.mreigar.data.repository.PostDetailsRepository
import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Success
import instrumentation.localdatasource.AvatarMemoryDataSourceInstrument.givenAvatarMemoryDataSource
import instrumentation.localdatasource.DatabaseDataSourceStatus
import instrumentation.localdatasource.EmailEmojiMemoryDataSourceInstrument.givenEmailEmojiMemoryDataSource
import org.assertj.core.api.Assertions
import org.junit.Test

class PostDetailsRepositoryTest {

    @Test
    fun `given repository, email emojis are retrieved with success result`() {
        val repository = PostDetailsRepository(
            givenEmailEmojiMemoryDataSource(),
            givenAvatarMemoryDataSource()
        )

        val result = repository.getEmailEmojis()

        Assertions.assertThat(result is Success).isTrue()
        Assertions.assertThat((result).dataStatus == DataStatus.LOCAL).isTrue()
        Assertions.assertThat((result as Success).data).isNotNull()
        Assertions.assertThat(result.data).isNotEmpty()
    }

    @Test
    fun `given repository, when email emojis not retrieved, empty success result`() {
        val repository = PostDetailsRepository(
            givenEmailEmojiMemoryDataSource(status = DatabaseDataSourceStatus.NO_DATA),
            givenAvatarMemoryDataSource()
        )

        val result = repository.getEmailEmojis()

        Assertions.assertThat(result is Success).isTrue()
        Assertions.assertThat((result).dataStatus == DataStatus.LOCAL).isTrue()
        Assertions.assertThat((result as Success).data).isNotNull()
        Assertions.assertThat(result.data).isEmpty()
    }

    @Test
    fun `given repository, avatar is returned with success result`() {
        val repository = PostDetailsRepository(
            givenEmailEmojiMemoryDataSource(),
            givenAvatarMemoryDataSource()
        )

        val result = repository.getAvatarUrl("user@mail.com")

        Assertions.assertThat(result is Success).isTrue()
        Assertions.assertThat((result).dataStatus == DataStatus.LOCAL).isTrue()
        Assertions.assertThat((result as Success).data).isNotNull()
        Assertions.assertThat(result.data).isNotEmpty()
    }
}