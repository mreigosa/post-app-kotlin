package com.mreigar.presentation.presenter

import com.mreigar.domain.executor.Invoker
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import com.mreigar.domain.executor.usecase.params.GetCommentsByPostUseCaseParams
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
    private val getCommentsByPostUseCase: GetCommentsByPostUseCase,
    invoker: Invoker
) : BasePresenter<PostDetailsViewTranslator>(WeakReference(view), invoker) {

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

    fun onRefreshCommentsClicked() {
        view()?.hideCommentsError()
        view()?.showLoader()
        fetchComments()
    }

    private fun fetchPostDetails() {
        view()?.showLoader()
        invoker.execute(this, getUserByPostUseCase withParams (post.userId)) {
            when (it) {
                is Success -> onUserRetrieved(it.data)
                else -> view()?.showError()
            }
        }
    }

    private fun onUserRetrieved(user: User) {
        view()?.showPostInfo(post)
        view()?.showUserInfo(userMapper.mapToView(user))
        fetchComments()
    }

    private fun fetchComments() {
        invoker.execute(this, getCommentsByPostUseCase withParams (GetCommentsByPostUseCaseParams(post.id, true))) {
            when (it) {
                is Success -> onCommentsRetrieved(it.data)
                else -> view()?.showCommentsError()
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
    fun showCommentsError()
    fun hideCommentsError()
}