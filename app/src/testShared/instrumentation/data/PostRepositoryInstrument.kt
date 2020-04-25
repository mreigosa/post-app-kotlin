package instrumentation.data

import com.mreigar.domain.executor.DataStatus
import com.mreigar.domain.executor.NoData
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.Post
import com.mreigar.domain.repository.PostRepositoryContract
import instrumentation.domain.DomainEntityInstrument.givenCommentList
import instrumentation.domain.DomainEntityInstrument.givenPostList

object PostRepositoryInstrument {

    fun givenPostRepository(
        status: RepositoryStatus = RepositoryStatus.SUCCESS,
        postList: List<Post>? = null,
        commentList: List<Comment>? = null
    ) = object : PostRepositoryContract {
        override fun getPosts(): Result<List<Post>> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(postList ?: givenPostList(1), DataStatus.REMOTE)
                RepositoryStatus.ERROR -> NoData
            }

        override fun getCommentsByPostId(postId: Int): Result<List<Comment>> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(commentList ?: givenCommentList(1), DataStatus.REMOTE)
                RepositoryStatus.ERROR -> NoData
            }
    }
}