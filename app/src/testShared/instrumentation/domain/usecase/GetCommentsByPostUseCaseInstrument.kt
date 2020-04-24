package instrumentation.domain.usecase

import com.mreigar.domain.executor.usecase.GetCommentsByPostUseCase
import com.mreigar.domain.model.Comment
import instrumentation.data.ComplementaryDetailsRepositoryInstrument
import instrumentation.data.PostRepositoryInstrument.givenPostRepository
import instrumentation.data.RepositoryStatus
import instrumentation.domain.TestContextProvider

object GetCommentsByPostUseCaseInstrument {

    fun givenGetCommentsByPostUseCase(
        repositoryStatus: RepositoryStatus,
        commentList: List<Comment>? = null
    ) = GetCommentsByPostUseCase(
        givenPostRepository(status = repositoryStatus, commentList = commentList),
        ComplementaryDetailsRepositoryInstrument.givenComplementaryDetailsRepository(),
        TestContextProvider()
    )
}