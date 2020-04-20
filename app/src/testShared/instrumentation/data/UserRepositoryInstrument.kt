package instrumentation.data

import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.User
import com.mreigar.domain.repository.UserRepositoryContract
import instrumentation.domain.DomainEntityInstrument.givenUser
import instrumentation.domain.DomainEntityInstrument.givenUserList

object UserRepositoryInstrument {

    fun givenUserRepository(
        status: RepositoryStatus = RepositoryStatus.SUCCESS,
        userList: List<User>? = null
    ) = object : UserRepositoryContract {
        override fun getUsers(): Result<List<User>> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(userList ?: givenUserList(1))
                RepositoryStatus.ERROR -> Error()
            }

        override fun getUserById(userId: Int): Result<User> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(givenUser())
                RepositoryStatus.ERROR -> Error()
            }
    }
}