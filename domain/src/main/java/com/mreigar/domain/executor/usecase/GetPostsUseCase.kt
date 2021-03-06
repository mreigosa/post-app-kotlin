package com.mreigar.domain.executor.usecase

import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.model.Post
import com.mreigar.domain.repository.PostRepositoryContract

class GetPostsUseCase(
    private val postRepository: PostRepositoryContract
) : UseCase<Nothing, List<Post>>() {

    override suspend fun run(): Result<List<Post>> {
        return postRepository.getPosts()
    }
}