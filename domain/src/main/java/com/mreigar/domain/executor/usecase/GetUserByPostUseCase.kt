package com.mreigar.domain.executor.usecase

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.model.User
import com.mreigar.domain.repository.PostDetailsRepositoryContract
import com.mreigar.domain.repository.UserRepositoryContract

class GetUserByPostUseCase(
    private val userRepository: UserRepositoryContract,
    private val postDetailsRepository: PostDetailsRepositoryContract,
    dispatcherProvider: DispatcherProvider
) : UseCase<Int, User>(dispatcherProvider) {

    override suspend fun run(params: Int?): Result<User> {
        if (params == null) return Error()

        val userResult = userRepository.getUserById(params)
        if (userResult is Success) {
            val avatarResult = postDetailsRepository.getAvatarUrl(userResult.data.email)
            if (avatarResult is Success) {
                val user = userResult.data.copy(avatarUrl = avatarResult.data)
                return Success(user, userResult.dataStatus)
            }
        }

        return userResult
    }
}