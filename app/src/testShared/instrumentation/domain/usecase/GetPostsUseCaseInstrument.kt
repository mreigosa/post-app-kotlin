package instrumentation.domain.usecase

import com.mreigar.domain.executor.usecase.GetPostsUseCase
import com.mreigar.domain.model.Post
import instrumentation.data.PostRepositoryInstrument
import instrumentation.data.PostRepositoryInstrument.givenPostRepository
import instrumentation.data.RepositoryStatus
import instrumentation.domain.TestContextProvider

object GetPostsUseCaseInstrument {

    fun givenGetPostsUseCase(
        repositoryStatus: RepositoryStatus,
        postList: List<Post>? = null
    ) = GetPostsUseCase(
        givenPostRepository(status = repositoryStatus, postList = postList),
        TestContextProvider()
    )
}