package instrumentation.domain

import com.mreigar.domain.executor.usecase.GetPostsUseCase
import com.mreigar.domain.model.Post
import instrumentation.data.PostRepositoryInstrument
import instrumentation.data.PostRepositoryStatus

object GetPostsUseCaseInstrument {

    fun givenGetPostsUseCase(
        repositoryStatus: PostRepositoryStatus,
        postList: List<Post>? = null
    ) = GetPostsUseCase(
        PostRepositoryInstrument.givenPostRepository(status = repositoryStatus, postList = postList),
        TestContextProvider()
    )
}