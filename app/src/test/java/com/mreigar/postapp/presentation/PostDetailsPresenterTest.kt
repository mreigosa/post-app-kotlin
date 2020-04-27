package com.mreigar.postapp.presentation

import instrumentation.presentation.PostDetailsCallbackResult
import instrumentation.presentation.PostDetailsPresenterInstrument.givenPostDetailsPresenter
import instrumentation.presentation.PostDetailsViewMethod
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.test.AutoCloseKoinTest

class PostDetailsPresenterTest : AutoCloseKoinTest() {

    private var callbackResult: PostDetailsCallbackResult = PostDetailsCallbackResult()

    @Test
    fun `given post details presenter, when screen is opened, post is obtained from args`() {
        val presenter = givenPostDetailsPresenter(callbackResult)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.GET_POST_FROM_ARGS)).isTrue()
    }

    @Test
    fun `given post details presenter, when screen is opened, if post is not obtained from args screen is closed`() {
        val presenter = givenPostDetailsPresenter(callbackResult, postFromArgs = null)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.GET_POST_FROM_ARGS)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.CLOSE_SCREEN)).isTrue()
    }

    @Test
    fun `given post details presenter, if user is retrieved, info is shown`() {
        val presenter = givenPostDetailsPresenter(callbackResult)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_LOADER)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_POST_INFO)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_USER_INFO)).isTrue()
    }

    @Test
    fun `given post details presenter, if user is not retrieved, error is shown`() {
        val presenter = givenPostDetailsPresenter(callbackResult, getUserIsSuccess = false)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_LOADER)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_ERROR)).isTrue()
    }

    @Test
    fun `given post details presenter, if comments are retrieved, info is shown`() {
        val presenter = givenPostDetailsPresenter(callbackResult)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_LOADER)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_POST_INFO)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_USER_INFO)).isTrue()
    }

    @Test
    fun `given post details presenter, when comments are retrieved, info are shown`() {
        val presenter = givenPostDetailsPresenter(callbackResult)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.HIDE_LOADER)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_COMMENTS)).isTrue()
    }

    @Test
    fun `given post details presenter, if comments are not retrieved, error is shown`() {
        val presenter = givenPostDetailsPresenter(callbackResult, getCommentsIsSuccess = false)

        presenter.onReady()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_LOADER)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_COMMENTS_ERROR)).isTrue()
    }

    @Test
    fun `given post details presenter, when refresh comments button clicked, error is hidden`() {
        val presenter = givenPostDetailsPresenter(callbackResult)

        presenter.onReady()
        callbackResult.clearFiredMethods()

        presenter.onRefreshCommentsClicked()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.HIDE_COMMENTS_ERROR)).isTrue()
        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.SHOW_LOADER)).isTrue()
    }

    @Test
    fun `given post details presenter, when refresh button clicked, error is hidden`() {
        val presenter = givenPostDetailsPresenter(callbackResult, getUserIsSuccess = false)

        presenter.onReady()
        presenter.onRefreshClicked()

        assertThat(callbackResult.isMethodFired(PostDetailsViewMethod.HIDE_ERROR)).isTrue()
    }
}