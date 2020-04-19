package instrumentation.data

import com.mreigar.data.model.PostEntity
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.Post
import com.mreigar.domain.repository.PostRepositoryContract
import instrumentation.domain.PostDomainInstrument.givenPost

enum class PostRepositoryStatus {
    SUCCESS, ERROR
}

object PostRepositoryInstrument {

    fun givenPostRepository(
        status: PostRepositoryStatus = PostRepositoryStatus.SUCCESS,
        postList: List<Post>? = null
    ) = object : PostRepositoryContract {
        override fun getPosts(): Result<List<Post>> =
            when (status) {
                PostRepositoryStatus.SUCCESS -> Success(postList ?: givenPostList())
                PostRepositoryStatus.ERROR -> Error()
            }
    }

    fun givenPostEntity() = PostEntity(1, 1, "title", "body")

    private fun givenPostList() = listOf(givenPost())
}