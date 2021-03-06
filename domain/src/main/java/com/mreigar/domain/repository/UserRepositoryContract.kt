package com.mreigar.domain.repository

import com.mreigar.domain.executor.Result
import com.mreigar.domain.model.User

interface UserRepositoryContract {
    fun getUserById(userId: Int): Result<User>
}