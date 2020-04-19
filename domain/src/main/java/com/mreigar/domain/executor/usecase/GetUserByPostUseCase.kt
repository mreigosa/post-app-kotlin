package com.mreigar.domain.executor.usecase

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.model.User
import com.mreigar.domain.repository.UserRepositoryContract

class GetUserByPostUseCase(
    private val userRepository: UserRepositoryContract,
    dispatcherProvider: DispatcherProvider
) : UseCase<Int, User>(dispatcherProvider) {

    override suspend fun run(params: Int?): Result<User> {
        return params?.let {
            userRepository.getUserById(params)
        } ?: Error()
    }
}