package com.mreigar.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mreigar.domain.executor.Invoker
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import com.mreigar.domain.executor.usecase.params.GetCommentsByPostUseCaseParams
import com.mreigar.domain.model.User
import com.mreigar.presentation.mapper.CommentViewModelMapper
import com.mreigar.presentation.mapper.UserViewModelMapper
import com.mreigar.presentation.model.PostViewModel

class PostDetailsViewModel(
        private val getUserByPostUseCase: GetUserByPostUseCase,
        private val getCommentsByPostUseCase: GetCommentsByPostUseCase,
        private val invoker: Invoker
) : ViewModel() {

    private val userMapper: UserViewModelMapper = UserViewModelMapper()
    private val commentMapper: CommentViewModelMapper = CommentViewModelMapper()

    private val _postDetailsViewState = MutableLiveData<PostDetailsViewState>()
    val postDetailsViewState: LiveData<PostDetailsViewState> = _postDetailsViewState

    private val _commentsViewState = MutableLiveData<PostCommentsViewState>()
    val commentsViewState: LiveData<PostCommentsViewState> = _commentsViewState

    private lateinit var post: PostViewModel

    fun onPostSelected(post: PostViewModel) {
        this.post = post
        fetchPostDetails()
    }

    fun onRefreshSelected() {
        fetchPostDetails()
    }

    fun onRefreshCommentsSelected() {
        _commentsViewState.value = PostCommentsViewState.Loading
        fetchComments()
    }

    private fun fetchPostDetails() {
        _postDetailsViewState.value = PostDetailsViewState.Loading
        invoker.execute(viewModelScope,
                getUserByPostUseCase withParams (post.userId)) {
            when (it) {
                is Success -> onUserRetrieved(it.data)
                else -> _postDetailsViewState.value = PostDetailsViewState.Error
            }
        }
    }

    private fun onUserRetrieved(user: User) {
        _postDetailsViewState.value = PostDetailsViewState.UserRetrieved(
                post = post,
                user = userMapper.mapToView(user)
        )
        fetchComments()
    }

    private fun fetchComments() {
        _commentsViewState.value = PostCommentsViewState.Loading
        val params = GetCommentsByPostUseCaseParams(post.id, true)
        invoker.execute(viewModelScope,
                getCommentsByPostUseCase withParams params) {
            when (it) {
                is Success -> _commentsViewState.value = PostCommentsViewState.CommentsRetrieved(it.data.map { commentMapper.mapToView(it) })
                else -> _commentsViewState.value = PostCommentsViewState.Error
            }
        }
    }
}
