package instrumentation.data

import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.Post
import com.mreigar.domain.model.PostUser
import com.mreigar.domain.repository.PostRepositoryContract
import instrumentation.domain.DomainEntityInstrument.givenCommentList
import instrumentation.domain.DomainEntityInstrument.givenPostList
import instrumentation.domain.DomainEntityInstrument.givenPostUserList

object PostRepositoryInstrument {

    fun givenPostRepository(
        status: RepositoryStatus = RepositoryStatus.SUCCESS,
        postList: List<Post>? = null,
        commentList: List<Comment>? = null,
        postUserList: List<PostUser>? = null
    ) = object : PostRepositoryContract {
        override fun getPosts(): Result<List<Post>> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(postList ?: givenPostList(1))
                RepositoryStatus.ERROR -> Error()
            }

        override fun getCommentsByPostId(postId: Int): Result<List<Comment>> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(commentList ?: givenCommentList(1))
                RepositoryStatus.ERROR -> Error()
            }

        override fun getPostsUsers(): Result<List<PostUser>> =
            when (status) {
                RepositoryStatus.SUCCESS -> Success(postUserList ?: givenPostUserList(1))
                RepositoryStatus.ERROR -> Error()
            }
    }
}