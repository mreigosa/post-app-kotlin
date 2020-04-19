package com.mreigar.presentation.presenter

import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.User
import com.mreigar.presentation.BasePresenter
import com.mreigar.presentation.mapper.CommentViewModelMapper
import com.mreigar.presentation.mapper.UserViewModelMapper
import com.mreigar.presentation.model.CommentViewModel
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.model.UserViewModel
import java.lang.ref.WeakReference

class PostDetailsPresenter(
    view: PostDetailsViewTranslator,
    private val getUserByPostUseCase: GetUserByPostUseCase,
    private val getCommentsByPostUseCase: GetCommentsByPostUseCase
) : BasePresenter<PostDetailsViewTranslator>(WeakReference(view)) {

    private val userMapper: UserViewModelMapper = UserViewModelMapper()
    private val commentMapper: CommentViewModelMapper = CommentViewModelMapper()

    private lateinit var post: PostViewModel

    override fun onReady() {
        super.onReady()
        view()?.getPostFromArgs()?.let {
            post = it
            fetchPostDetails()
        } ?: view()?.closeScreen()
    }

    private fun fetchPostDetails() {
        view()?.showLoader()
        getUserByPostUseCase.withParams(post.id).invoke(this) {
            when (it) {
                is Success -> onUserRetrieved(it.data)
                else -> view()?.showError()
            }
        }
    }

    private fun onUserRetrieved(user: User) {
        view()?.showPostInfo(post)
        view()?.showUserInfo(userMapper.mapToView(user))
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
    fun showUserInfo(user: UserViewModel)
    fun showComments(comments: List<CommentViewModel>)
}