package com.mreigar.presentation.presenter

import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.model.Comment
import com.mreigar.presentation.BasePresenter
import com.mreigar.presentation.mapper.CommentViewModelMapper
import com.mreigar.presentation.model.CommentViewModel
import com.mreigar.presentation.model.PostViewModel
import java.lang.ref.WeakReference

class PostDetailsPresenter(
    view: PostDetailsViewTranslator,
    private val getCommentsByPostUseCase: GetCommentsByPostUseCase
) : BasePresenter<PostDetailsViewTranslator>(WeakReference(view)) {

    private val commentMapper: CommentViewModelMapper = CommentViewModelMapper()

    private lateinit var post: PostViewModel

    override fun onReady() {
        super.onReady()
        view()?.getPostFromArgs()?.let {
            post = it
            onPostRetrieved()
        } ?: view()?.closeScreen()
    }

    private fun onPostRetrieved() {
        view()?.showLoader()
        view()?.showPostInfo(post)
        getCommentsByPostUseCase.withParams(post.id).invoke(this) {
            when (it) {
                is Success -> onCommentsRetrieved(it.data)
                else -> view()?.showError()
            }
        }
    }

    private fun onCommentsRetrieved(comments: List<Comment>) {
        view()?.hideLoader()
        view()?.showComments(comments.map { commentMapper.mapToView(it) })
    }
}

interface PostDetailsViewTranslator {
    fun getPostFromArgs(): PostViewModel?
    fun closeScreen()
    fun showLoader()
    fun hideLoader()
    fun showError()
    fun showPostInfo(post: PostViewModel)
    fun showComments(comments: List<CommentViewModel>)
}