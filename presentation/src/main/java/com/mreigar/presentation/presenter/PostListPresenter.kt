package com.mreigar.presentation.presenter

import com.mreigar.domain.executor.Invoker
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetPostsUseCase
import com.mreigar.presentation.BasePresenter
import com.mreigar.presentation.mapper.PostViewModelMapper
import com.mreigar.presentation.model.PostViewModel
import java.lang.ref.WeakReference

class PostListPresenter(
    view: PostListViewTranslator,
    private val getPostsUseCase: GetPostsUseCase,
    invoker: Invoker
) : BasePresenter<PostListViewTranslator>(WeakReference(view), invoker) {

    private val mapper: PostViewModelMapper = PostViewModelMapper()

    override fun onReady() {
        super.onReady()
        fetchPosts()
    }

    private fun fetchPosts() {
        view()?.showLoader()
        invoker.execute(this, getPostsUseCase) { result ->
            when {
                result is Success && result.data.isNotEmpty() -> view()?.showData(result.data.map { mapper.mapToView(it) })
                else -> view()?.showError()
            }
        }
    }

    fun onPostClicked(post: PostViewModel) {
        view()?.showPostDetails(post)
    }

    fun onRefreshClicked() {
        view()?.hideError()
        fetchPosts()
    }
}

interface PostListViewTranslator {
    fun showLoader()
    fun hideLoader()
    fun showData(posts: List<PostViewModel>)
    fun showError()
    fun showPostDetails(post: PostViewModel)
    fun hideError()
}