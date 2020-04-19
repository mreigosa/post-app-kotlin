package com.mreigar.postapp.domain

import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetPostsUseCase
import instrumentation.domain.TestContextProvider
import instrumentation.data.PostRepositoryInstrument
import instrumentation.data.PostRepositoryStatus
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetPostsUseCaseTest {

    @Test
    fun `given a use case then invoke with success result`() {
        val useCase = GetPostsUseCase(
            PostRepositoryInstrument.givenPostRepository(),
            TestContextProvider()
        )

        runBlocking {
            useCase.invoke(this) { result ->
                assertThat(result).isNotNull()
                assertThat(result is Success).isTrue()
            }
        }
    }

    @Test
    fun `given a use case then invoke with error result`() {
        val useCase = GetPostsUseCase(
            PostRepositoryInstrument.givenPostRepository(status = PostRepositoryStatus.ERROR),
            TestContextProvider()
        )

        runBlocking {
            useCase.invoke(this) { result ->
                assertThat(result).isNotNull()
                assertThat(result is Error).isTrue()
            }
        }
    }
}