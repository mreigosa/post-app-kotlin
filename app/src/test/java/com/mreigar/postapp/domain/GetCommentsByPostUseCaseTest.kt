package com.mreigar.postapp.domain

import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import instrumentation.data.PostRepositoryInstrument
import instrumentation.data.RepositoryStatus
import instrumentation.domain.TestContextProvider
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test

class GetCommentsByPostUseCaseTest {

    @Test
    fun `given a use case then invoke without params returns error result`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(),
            TestContextProvider()
        )

        runBlocking {
            useCase.invoke(this) { result ->
                Assertions.assertThat(result is Error).isTrue()
            }
        }
    }

    @Test
    fun `given a use case then invoke with success result`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(),
            TestContextProvider()
        )

        runBlocking {
            useCase.withParams(1).invoke(this) { result ->
                Assertions.assertThat(result).isNotNull()
                Assertions.assertThat(result is Success).isTrue()
            }
        }
    }

    @Test
    fun `given a use case then invoke with no data result`() {
        val useCase = GetCommentsByPostUseCase(
            PostRepositoryInstrument.givenPostRepository(status = RepositoryStatus.ERROR),
            TestContextProvider()
        )

        runBlocking {
            useCase.withParams(1).invoke(this) { result ->
                Assertions.assertThat(result).isNotNull()
                Assertions.assertThat(result is NoData).isTrue()
            }
        }
    }
}