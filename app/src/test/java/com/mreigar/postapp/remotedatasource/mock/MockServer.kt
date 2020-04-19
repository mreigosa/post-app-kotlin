package com.mreigar.postapp.remotedatasource.mock

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import java.io.IOException

class MockServer private constructor() {

    companion object {
        fun create(): MockServer {
            return MockServer()
        }
    }

    private val mockWebServer: MockWebServer = MockWebServer()

    fun enqueue(code: Int): MockServer {
        val response = MockResponse().setResponseCode(code)
        enqueue(response)
        return this
    }

    fun enqueue(code: Int, body: String): MockServer {
        val response = MockResponse()
            .setResponseCode(code)
            .setBody(body)
        enqueue(response)
        return this
    }

    @Throws(IOException::class)
    fun enqueueFile(fileName: String): MockServer {
        enqueueFile(200, fileName)
        return this
    }

    @Throws(IOException::class)
    fun enqueueFile(code: Int, fileName: String): MockServer {
        val `in` = this.javaClass.classLoader!!.getResourceAsStream(fileName)
        val buffer = `in`.source().buffer()
        enqueue(code, buffer.readUtf8())
        return this
    }

    fun enqueue(response: MockResponse): MockServer {
        mockWebServer.enqueue(response)
        return this
    }

    fun captureRequest(): RecordedRequest {
        return mockWebServer.takeRequest()
    }

    @Throws(IOException::class)
    fun start(): String {
        mockWebServer.start()
        return mockWebServer.url("/").toString()
    }

    @Throws(IOException::class)
    fun shutdown() {
        mockWebServer.shutdown()
    }
}