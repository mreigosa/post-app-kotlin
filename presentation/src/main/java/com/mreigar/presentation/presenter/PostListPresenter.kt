package com.mreigar.presentation.presenter

import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetPostsUsersUseCase
import com.mreigar.presentation.BasePresenter
import com.mreigar.presentation.mapper.PostViewModelMapper
import com.mreigar.presentation.model.PostViewModel
import java.lang.ref.WeakReference

class PostListPresenter(
    view: PostListViewTranslator,
    private val getPostsUsersUseCase: GetPostsUsersUseCase
) : BasePresenter<PostListViewTranslator>(WeakReference(view)) {

    private val mapper: PostViewModelMapper = PostViewModelMapper()

    override fun onReady() {
        super.onReady()
        fetchPostsAndUsers()
    }

    private fun fetchPostsAndUsers() {
        view()?.showLoader()
        getPostsUsersUseCase.invoke(this) { result ->
            when (result) {
                is Success -> view()?.showData(result.data.map { mapper.mapToView(it) })
                else -> view()?.showError()
            }
        }
    }

    fun onPostClicked(post: PostViewModel) {
        view()?.showPostDetails(post)
    }
}

interface PostListViewTranslator {
    fun showLoader()
    fun hideLoader()
    fun showData(posts: List<PostViewModel>)
    fun showError()
    fun showPostDetails(post: PostViewModel)
}