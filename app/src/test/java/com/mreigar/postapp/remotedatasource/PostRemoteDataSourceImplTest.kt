package com.mreigar.postapp.remotedatasource

import com.mreigar.network.api.NetworkApi
import com.mreigar.network.api.PostApi
import com.mreigar.network.datasource.PostRemoteDataSourceImpl
import com.mreigar.postapp.remotedatasource.mock.MockServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

class PostRemoteDataSourceImplTest : AutoCloseKoinTest() {

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
    fun `given success response, posts are retrieved`() {
        mockServer.enqueueFile("getPosts.json")
        val response = PostRemoteDataSourceImpl(postApi).getPosts()

        assertThat(response).isNotNull
        assertThat(response).isNotEmpty
    }

    @Test(expected = Exception::class)
    fun `that cannot fetch  posts`() {
        mockServer.enqueue(500)
        PostRemoteDataSourceImpl(postApi).getPosts()
    }
}