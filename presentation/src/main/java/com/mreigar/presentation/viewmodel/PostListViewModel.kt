package com.mreigar.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mreigar.domain.executor.Invoker
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.usecase.GetPostsUseCase
import com.mreigar.domain.model.Post
import com.mreigar.presentation.mapper.PostViewModelMapper
import com.mreigar.presentation.model.PostViewModel

class PostListViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val invoker: Invoker
) : ViewModel() {

    private val mapper: PostViewModelMapper = PostViewModelMapper()

    private val _postListViewState = MutableLiveData<PostListViewState>()
    val postListViewState: LiveData<PostListViewState> = _postListViewState

    fun onDataRequested(){
        fetchPosts()
    }

    fun onRefreshSelected() {
        fetchPosts()
    }

    private fun fetchPosts() {
        _postListViewState.value = PostListViewState.Loading
        invoker.execute(viewModelScope, getPostsUseCase) { result ->
            when {
                result is Success && result.data.isNotEmpty() -> onPostsFetched(result.data)
                else -> _postListViewState.value = PostListViewState.Error
            }
        }
    }

    private fun onPostsFetched(posts: List<Post>) {
        posts.map { mapper.mapToView(it) }.also {
            _postListViewState.value = PostListViewState.Success(it)
        }
    }
}
