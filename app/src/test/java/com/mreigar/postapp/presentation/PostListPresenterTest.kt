package com.mreigar.postapp.presentation

import com.mreigar.presentation.presenter.PostListPresenter
import instrumentation.domain.PostDomainInstrument.givenPostList
import instrumentation.presentation.PostListCallbackResult
import instrumentation.presentation.PostListPresenterInstrument
import instrumentation.presentation.PostListViewMethod
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.test.AutoCloseKoinTest

class PostListPresenterTest : AutoCloseKoinTest() {

    private lateinit var presenter: PostListPresenter
    private var callbackResult: PostListCallbackResult = PostListCallbackResult()

    @Test
    fun `given post list presenter, when data is retrieved, then is shown`() {
        val listSize = 5
        presenter = PostListPresenterInstrument.givenPostListPresenter(callbackResult, true, givenPostList(listSize))

        presenter.onStart()

        assertThat(callbackResult.isMethodFired(PostListViewMethod.SHOW_LOADER)).isTrue()
        assertThat(callbackResult.isMethodFired(PostListViewMethod.SHOW_DATA) {
            (params as List<*>).size == listSize
        }).isTrue()
    }

    @Test
    fun `given post list presenter, when data is not retrieved, then error is shown`() {
        presenter = PostListPresenterInstrument.givenPostListPresenter(callbackResult, false)

        presenter.onStart()

        assertThat(callbackResult.isMethodFired(PostListViewMethod.SHOW_LOADER)).isTrue()
        assertThat(callbackResult.isMethodFired(PostListViewMethod.SHOW_DATA)).isFalse()
        assertThat(callbackResult.isMethodFired(PostListViewMethod.SHOW_ERROR)).isTrue()
    }

}