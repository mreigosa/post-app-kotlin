package instrumentation.localdatasource

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity

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
    }
}