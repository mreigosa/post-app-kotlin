package instrumentation.domain

import com.mreigar.domain.executor.usecase.GetPostsUsersUseCase
import com.mreigar.domain.model.PostUser
import instrumentation.data.PostRepositoryInstrument
import instrumentation.data.RepositoryStatus
import instrumentation.data.UserRepositoryInstrument

object GetPostsUsersUseCaseInstrument {

    fun givenGetPostsUsersUseCase(
        postRepositoryStatus: RepositoryStatus,
        userRepositoryStatus: RepositoryStatus,
        postUserList: List<PostUser>? = null
    ) = GetPostsUsersUseCase(
        PostRepositoryInstrument.givenPostRepository(status = postRepositoryStatus, postUserList = postUserList),
        UserRepositoryInstrument.givenUserRepository(status = userRepositoryStatus),
        TestContextProvider()
    )
}