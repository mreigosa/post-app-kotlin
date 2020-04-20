package instrumentation.localdatasource

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import com.mreigar.data.model.PostUserEntity

enum class PostLocalDataSourceStatus {
    SUCCESS, ERROR
}

object PostLocalDataSourceInstrument {

    fun givenPostDatabaseDataSource(
        status: PostLocalDataSourceStatus = PostLocalDataSourceStatus.SUCCESS,
        configuration: PostDatabaseDataSourceConfiguration = PostDatabaseDataSourceConfiguration()
    ): PostDatabaseDataSourceContract = object : PostDatabaseDataSourceContract {
        override fun getPosts(): List<PostEntity> =
            when (status) {
                PostLocalDataSourceStatus.SUCCESS -> configuration.postEntityList
                PostLocalDataSourceStatus.ERROR -> throw Exception()
            }

        override fun savePosts(posts: List<PostEntity>) = Unit

        override fun getCommentsByPostId(postId: Int): List<CommentEntity> =
            when (status) {
                PostLocalDataSourceStatus.SUCCESS -> configuration.commentEntityList
                PostLocalDataSourceStatus.ERROR -> throw Exception()
            }

        override fun saveComments(comments: List<CommentEntity>) = Unit

        override fun getPostsUsers(): List<PostUserEntity> =
            when (status) {
                PostLocalDataSourceStatus.SUCCESS -> configuration.postUserEntityList
                PostLocalDataSourceStatus.ERROR -> throw Exception()
            }
    }
}