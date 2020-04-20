package instrumentation.data

import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.Post
import com.mreigar.domain.repository.PostRepositoryContract
import instrumentation.domain.PostDomainInstrument.givenCommentList
import instrumentation.domain.PostDomainInstrument.givenPostList

enum class PostRepositoryStatus {
    SUCCESS, ERROR
}

object PostRepositoryInstrument {

    fun givenPostRepository(
        status: PostRepositoryStatus = PostRepositoryStatus.SUCCESS,
        postList: List<Post>? = null,
        commentList: List<Comment>? = null
    ) = object : PostRepositoryContract {
        override fun getPosts(): Result<List<Post>> =
            when (status) {
                PostRepositoryStatus.SUCCESS -> Success(postList ?: givenPostList(1))
                PostRepositoryStatus.ERROR -> Error()
            }

        override fun getCommentsByPostId(postId: Int): Result<List<Comment>> =
            when (status) {
                PostRepositoryStatus.SUCCESS -> Success(commentList ?: givenCommentList(1))
                PostRepositoryStatus.ERROR -> Error()
            }
    }
}