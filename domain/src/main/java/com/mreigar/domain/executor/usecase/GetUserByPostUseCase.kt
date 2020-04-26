package com.mreigar.domain.executor.usecase

import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.model.User
import com.mreigar.domain.repository.PostDetailsRepositoryContract
import com.mreigar.domain.repository.UserRepositoryContract

class GetUserByPostUseCase(
    private val userRepository: UserRepositoryContract,
    private val postDetailsRepository: PostDetailsRepositoryContract
) : UseCase<Int, User>() {

    override suspend fun run(): Result<User> {
        val userResult = userRepository.getUserById(useCaseParams)
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