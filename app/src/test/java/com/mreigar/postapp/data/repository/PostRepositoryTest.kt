package com.mreigar.postapp.data.repository

import com.mreigar.data.repository.PostRepository
import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Success
import instrumentation.data.DataEntityInstrument.givenCommentEntity
import instrumentation.localdatasource.DatabaseDataSourceStatus
import instrumentation.localdatasource.PostDatabaseDataSourceInstrument
import instrumentation.remotedatasource.configuration.PostRemoteDataSourceConfiguration
import instrumentation.remotedatasource.PostRemoteDataSourceInstrument
import instrumentation.remotedatasource.RemoteDataSourceStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostRepositoryTest {

    @Test
    fun `given post repository, when retrieve posts from remote, then success result`() {
        val repository = PostRepository(
            PostRemoteDataSourceInstrument.givenPostRemoteDataSource(),
            PostDatabaseDataSourceInstrument.givenPostDatabaseDataSource()
        )

        val result = repository.getPosts()

        assertThat(result is Success).isTrue()
        assertThat((result).dataStatus == DataStatus.REMOTE).isTrue()
        assertThat((result as Success).data).isNotNull()
        assertThat(result.data).isNotEmpty()
    }

    @Test
    fun `given post repository, when retrieve posts from remote fails, local data is returned`() {
        val repository = PostRepository(
            PostRemoteDataSourceInstrument.givenPostRemoteDataSource(RemoteDataSourceStatus.ERROR),
            PostDatabaseDataSourceInstrument.givenPostDatabaseDataSource()
        )

        val result = repository.getPosts()

        assertThat(result is Success).isTrue()
        assertThat((result).dataStatus == DataStatus.LOCAL).isTrue()
        assertThat((result as Success).data).isNotNull()
        assertThat(result.data).isNotEmpty()
    }

    @Test
    fun `that can get an error when posts are not retrieved`() {
        val repository = PostRepository(
            PostRemoteDataSourceInstrument.givenPostRemoteDataSource(RemoteDataSourceStatus.ERROR),
            PostDatabaseDataSourceInstrument.givenPostDatabaseDataSource(DatabaseDataSourceStatus.NO_DATA)
        )

        val result = repository.getPosts()

        assertThat(result is NoData).isTrue()
    }

    @Test
    fun `given post repository, when retrieve comments by post id, then success result`() {
        val repository = PostRepository(
            PostRemoteDataSourceInstrument.givenPostRemoteDataSource(
                configuration = PostRemoteDataSourceConfiguration(
                    commentEntityList = listOf(givenCommentEntity(id = 1, postId = 1), givenCommentEntity(id = 2, postId = 1))
                )
            ),
            PostDatabaseDataSourceInstrument.givenPostDatabaseDataSource()
        )

        val result = repository.getCommentsByPostId(1)

        assertThat(result is Success).isTrue()
        assertThat((result).dataStatus == DataStatus.REMOTE).isTrue()
        assertThat((result as Success).data).isNotNull()
        assertThat(result.data).isNotEmpty()
        assertThat(result.data.size).isEqualTo(2)
    }

    @Test
    fun `given post repository, when retrieve comments by post id, if remote refresh fails and local data available then success result`() {
        val repository = PostRepository(
            PostRemoteDataSourceInstrument.givenPostRemoteDataSource(RemoteDataSourceStatus.ERROR),
            PostDatabaseDataSourceInstrument.givenPostDatabaseDataSource()
        )

        val result = repository.getCommentsByPostId(1)

        assertThat(result is Success).isTrue()
        assertThat((result).dataStatus == DataStatus.LOCAL).isTrue()
        assertThat((result as Success).data).isNotNull()
        assertThat(result.data).isNotEmpty()
    }

    @Test
    fun `given post repository, when retrieve comments by post id not retrieved, then no data result`() {
        val repository = PostRepository(
            PostRemoteDataSourceInstrument.givenPostRemoteDataSource(),
            PostDatabaseDataSourceInstrument.givenPostDatabaseDataSource(DatabaseDataSourceStatus.NO_DATA)
        )

        val result = repository.getCommentsByPostId(1)

        assertThat(result is NoData).isTrue()
    }
}