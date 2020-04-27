package com.mreigar.postapp.domain

import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetPostsUseCase
import instrumentation.data.PostRepositoryInstrument
import instrumentation.data.RepositoryStatus
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetPostsUseCaseTest {

    @Test
    fun `given a use case then invoke with success result`() {
        val useCase = GetPostsUseCase(PostRepositoryInstrument.givenPostRepository())

        runBlocking {
            val result = useCase.run()
            assertThat(result).isNotNull()
            assertThat(result is Success).isTrue()
        }
    }

    @Test
    fun `given a use case then invoke with error result`() {
        val useCase = GetPostsUseCase(PostRepositoryInstrument.givenPostRepository(status = RepositoryStatus.ERROR))

        runBlocking {
            val result = useCase.run()
            assertThat(result).isNotNull()
            assertThat(result is NoData).isTrue()
        }
    }
}