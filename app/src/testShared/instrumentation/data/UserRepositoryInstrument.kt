package instrumentation.data

import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.User
import com.mreigar.domain.repository.UserRepositoryContract
import instrumentation.domain.DomainEntityInstrument.givenUser

object UserRepositoryInstrument {

    fun givenUserRepository(
        status: RepositoryStatus = RepositoryStatus.SUCCESS,
        user: User? = null
    ): UserRepositoryContract = object : UserRepositoryContract {
        override fun getUserById(userId: Int): Result<User> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(user ?: givenUser(1), DataStatus.LOCAL)
                RepositoryStatus.ERROR -> NoData
            }
    }
}