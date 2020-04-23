package com.mreigar.postapp.domain

import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.executor.usecase.params.GetCommentsByPostUseCaseParams
import com.mreigar.domain.model.EmailEmoji
import com.mreigar.domain.model.EmailPattern
import instrumentation.data.ComplementaryDetailsRepositoryInstrument
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
            ComplementaryDetailsRepositoryInstrument.givenComplementaryDetailsRepository(),
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
            ComplementaryDetailsRepositoryInstrument.givenComplementaryDetailsRepository(),
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
            ComplementaryDetailsRepositoryInstrument.givenComplementaryDetailsRepository(),
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
            ComplementaryDetailsRepositoryInstrument.givenComplementaryDetailsRepository(
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
                    Assertions.assertThat(result.data[1].details).isNull()
                }
        }
    }

    @Test
    fun `given a use case, if no complementary info retrieved for pattern, valid email without details`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(
                commentList = listOf(givenComment(email = "martin@mail.info"), givenComment(email = "martin@mail.co.uk"))
            ),
            ComplementaryDetailsRepositoryInstrument.givenComplementaryDetailsRepository(
                emailEmojis = listOf(EmailEmoji(EmailPattern.UK, "uk-emoji"))
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
                    Assertions.assertThat(result.data[0].details).isNull()
                    Assertions.assertThat(result.data[1].details).isNotNull
                }
        }
    }
}