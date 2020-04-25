package com.mreigar.postapp.domain

import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.executor.usecase.params.GetCommentsByPostUseCaseParams
import com.mreigar.domain.model.EmailEmoji
import com.mreigar.domain.model.EmailPattern
import instrumentation.data.PostDetailsRepositoryInstrument
import instrumentation.data.PostRepositoryInstrument
import instrumentation.data.RepositoryStatus
import instrumentation.domain.DomainEntityInstrument.givenComment
import instrumentation.domain.TestContextProvider
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test

class GetCommentsByPostUseCaseTest {

    @Test
    fun `given a use case then invoke without params returns error result`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(),
            PostDetailsRepositoryInstrument.givenPostDetailsRepository(),
            TestContextProvider()
        )

        runBlocking {
            useCase
                .invoke(this) { result ->
                    Assertions.assertThat(result is Error).isTrue()
                }
        }
    }

    @Test
    fun `given a use case then invoke with success result`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(),
            PostDetailsRepositoryInstrument.givenPostDetailsRepository(),
            TestContextProvider()
        )

        runBlocking {
            useCase
                .withParams(GetCommentsByPostUseCaseParams(1, false))
                .invoke(this) { result ->
                    Assertions.assertThat(result).isNotNull()
                    Assertions.assertThat(result is Success).isTrue()
                }
        }
    }

    @Test
    fun `given a use case then invoke with no data result`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(status = RepositoryStatus.ERROR),
            PostDetailsRepositoryInstrument.givenPostDetailsRepository(),
            TestContextProvider()
        )

        runBlocking {
            useCase
                .withParams(GetCommentsByPostUseCaseParams(1, false))
                .invoke(this) { result ->
                    Assertions.assertThat(result).isNotNull()
                    Assertions.assertThat(result is NoData).isTrue()
                }
        }
    }

    @Test
    fun `given a use case, comments are retrieved with details for valid emails`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(
                commentList = listOf(givenComment(email = "martin@mail.info"), givenComment(email = "martin@mail.com"))
            ),
            PostDetailsRepositoryInstrument.givenPostDetailsRepository(
                emailEmojis = listOf(EmailEmoji(EmailPattern.INFO, "fake-emoji"))
            ),
            TestContextProvider()
        )

        runBlocking {
            useCase
                .withParams(GetCommentsByPostUseCaseParams(1, true))
                .invoke(this) { result ->
                    Assertions.assertThat(result).isNotNull()
                    Assertions.assertThat(result is Success).isTrue()
                    Assertions.assertThat((result as Success).data).isNotNull()
                    Assertions.assertThat(result.data).isNotEmpty()
                    Assertions.assertThat(result.data[0].details).isNotNull()
                    Assertions.assertThat(result.data[0].details!!.emojis).isNotNull()
                    Assertions.assertThat(result.data[1].details).isNotNull()
                    Assertions.assertThat(result.data[1].details!!.emojis).isNull()
                }
        }
    }

    @Test
    fun `given a use case, avatar info is retrieved`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(commentList = listOf(givenComment(email = "martin@mail.info"))),
            PostDetailsRepositoryInstrument.givenPostDetailsRepository(avatarUrl = "email.avatar.com"),
            TestContextProvider()
        )

        runBlocking {
            useCase
                .withParams(GetCommentsByPostUseCaseParams(1, true))
                .invoke(this) { result ->
                    Assertions.assertThat(result).isNotNull()
                    Assertions.assertThat(result is Success).isTrue()
                    Assertions.assertThat((result as Success).data).isNotNull()
                    Assertions.assertThat(result.data).isNotEmpty()
                    Assertions.assertThat(result.data[0].details).isNotNull()
                    Assertions.assertThat(result.data[0].details!!.avatarUrl).isNotBlank()
                    Assertions.assertThat(result.data[0].details!!.avatarUrl).isEqualTo("email.avatar.com")
                }
        }
    }
}