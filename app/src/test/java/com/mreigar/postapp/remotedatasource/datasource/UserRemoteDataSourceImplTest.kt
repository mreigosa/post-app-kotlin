package com.mreigar.postapp.remotedatasource.datasource

import com.mreigar.network.api.NetworkApi
import com.mreigar.network.api.PostApi
import com.mreigar.network.datasource.UserRemoteDataSourceImpl
import com.mreigar.postapp.remotedatasource.mock.MockServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

class UserRemoteDataSourceImplTest : AutoCloseKoinTest() {

    private lateinit var mockServer: MockServer

    private lateinit var postApi: PostApi

    @Before
    fun before() {
        mockServer = MockServer.create()

        postApi = NetworkApi().provideApi(
            mockServer.start(),
            PostApi::class.java
        )

        startKoin {
            modules(
                listOf(
                    module {
                        single(override = true) { postApi }
                    }
                )
            )
        }
    }

    @After
    fun after() {
        mockServer.shutdown()
    }

    @Test
    fun `given success response, users are retrieved`() {
        mockServer.enqueueFile("getUsers.json")
        val response = UserRemoteDataSourceImpl(postApi).getUsers()

        assertThat(response).isNotNull
        assertThat(response).isNotEmpty
    }

    @Test(expected = Exception::class)
    fun `that cannot fetch  comments`() {
        mockServer.enqueue(500)
        UserRemoteDataSourceImpl(postApi).getUsers()
    }
}