package instrumentation.domain.usecase

import com.mreigar.domain.executor.usecase.GetUserByPostUseCase
import com.mreigar.domain.model.User
import instrumentation.data.RepositoryStatus
import instrumentation.data.UserRepositoryInstrument.givenUserRepository
import instrumentation.domain.TestContextProvider

object GetUserByPostUseCaseInstrument {

    fun givenGetUserByPostUseCase(
        repositoryStatus: RepositoryStatus,
        user: User? = null
    ) = GetUserByPostUseCase(
        givenUserRepository(status = repositoryStatus, user = user),
        TestContextProvider()
    )
}