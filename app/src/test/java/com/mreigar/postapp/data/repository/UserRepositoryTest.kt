package com.mreigar.postapp.data.repository

import com.mreigar.data.repository.UserRepository
import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Success
import instrumentation.data.DataEntityInstrument.givenUserEntity
import instrumentation.localdatasource.DatabaseDataSourceStatus
import instrumentation.localdatasource.UserDatabaseDataSourceConfiguration
import instrumentation.localdatasource.UserDatabaseDataSourceInstrument
import instrumentation.remotedatasource.UserRemoteDataSourceConfiguration
import instrumentation.remotedatasource.UserRemoteDataSourceInstrument
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UserRepositoryTest {

    @Test
    fun `given user repository, when retrieve user by id, then success result`() {
        val repository = UserRepository(
            UserRemoteDataSourceInstrument.givenUserRemoteDataSource(),
            UserDatabaseDataSourceInstrument.givenUserDatabaseDataSource(
                configuration = UserDatabaseDataSourceConfiguration(userEntity = givenUserEntity(userId = 1))
            )
        )

        val result = repository.getUserById(1)

        assertThat(result is Success).isTrue()
        assertThat((result).dataStatus == DataStatus.LOCAL).isTrue()
        assertThat((result as Success).data).isNotNull
    }

    @Test
    fun `given user repository, when retrieve user by id not retrieved from database, retrieve data from remote is success`() {
        val repository = UserRepository(
            UserRemoteDataSourceInstrument.givenUserRemoteDataSource(configuration = UserRemoteDataSourceConfiguration(userEntityList = listOf(givenUserEntity(userId = 1)))),
            UserDatabaseDataSourceInstrument.givenUserDatabaseDataSource(DatabaseDataSourceStatus.NO_DATA)
        )

        val result = repository.getUserById(1)

        assertThat(result is Success).isTrue()
        assertThat((result).dataStatus == DataStatus.REMOTE).isTrue()
        assertThat((result as Success).data).isNotNull
    }

    @Test
    fun `given user repository, when retrieve user by id not retrieved from database or remote, no data result`() {
        val repository = UserRepository(
            UserRemoteDataSourceInstrument.givenUserRemoteDataSource(configuration = UserRemoteDataSourceConfiguration(userEntityList = listOf(givenUserEntity(userId = 1)))),
            UserDatabaseDataSourceInstrument.givenUserDatabaseDataSource(DatabaseDataSourceStatus.NO_DATA)
        )

        val result = repository.getUserById(2)

        assertThat(result is NoData).isTrue()
    }
}