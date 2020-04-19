package com.mreigar.domain.executor.usecase

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.model.Post
import com.mreigar.domain.repository.PostRepositoryContract

class GetPostsUseCase(
    private val postRepository: PostRepositoryContract,
    dispatcherProvider: DispatcherProvider
) : UseCase<Nothing, List<Post>>(dispatcherProvider) {

    override suspend fun run(params: Nothing?): Result<List<Post>> {
        return postRepository.getPosts()
    }
}