package com.mreigar.domain.executor.usecase

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.model.PostUser
import com.mreigar.domain.repository.PostRepositoryContract
import com.mreigar.domain.repository.UserRepositoryContract

class GetPostsUsersUseCase(
    private val postRepositoryContract: PostRepositoryContract,
    private val userRepository: UserRepositoryContract,
    dispatcherProvider: DispatcherProvider
) : UseCase<Nothing, List<PostUser>>(dispatcherProvider) {

    override suspend fun run(params: Nothing?): Result<List<PostUser>> {
        postRepositoryContract.getPosts()
        userRepository.getUsers()

        return postRepositoryContract.getPostsUsers()
    }
}