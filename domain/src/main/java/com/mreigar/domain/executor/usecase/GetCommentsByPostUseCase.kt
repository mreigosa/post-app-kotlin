package com.mreigar.domain.executor.usecase

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.model.Comment
import com.mreigar.domain.repository.PostRepositoryContract

class GetCommentsByPostUseCase(
    private val postRepository: PostRepositoryContract,
    dispatcherProvider: DispatcherProvider
) : UseCase<Int, List<Comment>>(dispatcherProvider) {

    override suspend fun run(params: Int?): Result<List<Comment>> {
        return params?.let {
            postRepository.getCommentsByPostId(params)
        } ?: Error()
    }
}