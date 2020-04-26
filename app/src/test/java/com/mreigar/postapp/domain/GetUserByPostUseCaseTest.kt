package com.mreigar.postapp.domain

import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import instrumentation.data.PostDetailsRepositoryInstrument.givenPostDetailsRepository
import instrumentation.data.RepositoryStatus
import instrumentation.data.UserRepositoryInstrument.givenUserRepository
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetUserByPostUseCaseTest {

    @Test
    fun `given a use case then invoke with success result`() {
        val useCase = GetUserByPostUseCase(givenUserRepository(), givenPostDetailsRepository())

        runBlocking {
            val result = useCase.withParams(1).run()
            assertThat(result).isNotNull()
            assertThat(result is Success).isTrue()
            assertThat((result as Success).data).isNotNull()
        }
    }

    @Test
    fun `given a use case then invoke with no data result`() {
        val useCase = GetUserByPostUseCase(givenUserRepository(status = RepositoryStatus.ERROR), givenPostDetailsRepository())

        runBlocking {
            val result = useCase.withParams(1).run()
            assertThat(result).isNotNull()
            assertThat(result is NoData).isTrue()
        }
    }

    @Test
    fun `given a use case then avatar info is retrieved`() {
        val useCase = GetUserByPostUseCase(givenUserRepository(), givenPostDetailsRepository(avatarUrl = "email.avatar.com"))

        runBlocking {
            val result = useCase.withParams(1).run()
            assertThat(result).isNotNull()
            assertThat(result is Success).isTrue()
            assertThat((result as Success).data).isNotNull()
            assertThat(result.data.avatarUrl).isNotBlank()
        }
    }
}