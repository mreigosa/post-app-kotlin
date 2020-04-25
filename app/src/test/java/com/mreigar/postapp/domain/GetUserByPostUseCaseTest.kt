package com.mreigar.postapp.domain

import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import instrumentation.data.PostDetailsRepositoryInstrument.givenPostDetailsRepository
import instrumentation.data.RepositoryStatus
import instrumentation.data.UserRepositoryInstrument
import instrumentation.domain.TestContextProvider
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test

class GetUserByPostUseCaseTest {

    @Test
    fun `given a use case then invoke without params returns error result`() {
        val useCase = GetUserByPostUseCase(
            UserRepositoryInstrument.givenUserRepository(),
            givenPostDetailsRepository(),
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
        val useCase = GetUserByPostUseCase(
            UserRepositoryInstrument.givenUserRepository(),
            givenPostDetailsRepository(),
            TestContextProvider()
        )

        runBlocking {
            useCase.withParams(1).invoke(this) { result ->
                Assertions.assertThat(result).isNotNull()
                Assertions.assertThat(result is Success).isTrue()
                Assertions.assertThat((result as Success).data).isNotNull()
            }
        }
    }

    @Test
    fun `given a use case then invoke with no data result`() {
        val useCase = GetUserByPostUseCase(
            UserRepositoryInstrument.givenUserRepository(status = RepositoryStatus.ERROR),
            givenPostDetailsRepository(),
            TestContextProvider()
        )

        runBlocking {
            useCase.withParams(1).invoke(this) { result ->
                Assertions.assertThat(result).isNotNull()
                Assertions.assertThat(result is NoData).isTrue()
            }
        }
    }

    @Test
    fun `given a use case then avatar info is retrieved`() {
        val useCase = GetUserByPostUseCase(
            UserRepositoryInstrument.givenUserRepository(),
            givenPostDetailsRepository(avatarUrl = "email.avatar.com"),
            TestContextProvider()
        )

        runBlocking {
            useCase.withParams(1).invoke(this) { result ->
                Assertions.assertThat(result).isNotNull()
                Assertions.assertThat(result is Success).isTrue()
                Assertions.assertThat((result as Success).data).isNotNull()
                Assertions.assertThat(result.data.avatarUrl).isNotBlank()
            }
        }
    }
}