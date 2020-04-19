package com.mreigar.postapp.data

import com.mreigar.data.repository.PostRepository
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Success
import instrumentation.remotedatasource.PostDataSourceStatus
import instrumentation.remotedatasource.PostRemoteDataSourceInstrument
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostRepositoryTest {

    @Test
    fun `given lego repository, when retrieve lego themes from remote, then success result`() {
        val repository = PostRepository(PostRemoteDataSourceInstrument.givenPostRemoteDataSource())

        val result = repository.getPosts()

        assertThat(result is Success).isTrue()
        assertThat((result as Success).data).isNotNull()
        assertThat(result.data).isNotEmpty()
    }

    @Test
    fun `that can  get an error response`() {
        val repository = PostRepository(PostRemoteDataSourceInstrument.givenPostRemoteDataSource(PostDataSourceStatus.ERROR))

        val result = repository.getPosts()

        assertThat(result is Error).isTrue()
    }
}