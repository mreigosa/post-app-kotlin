package instrumentation.localdatasource

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import instrumentation.localdatasource.configuration.PostDatabaseDataSourceConfiguration

object PostDatabaseDataSourceInstrument {

    fun givenPostDatabaseDataSource(
        status: DatabaseDataSourceStatus = DatabaseDataSourceStatus.SUCCESS,
        configuration: PostDatabaseDataSourceConfiguration = PostDatabaseDataSourceConfiguration()
    ): PostDatabaseDataSourceContract = object : PostDatabaseDataSourceContract {
        override fun getPosts(): List<PostEntity> =
            when (status) {
                DatabaseDataSourceStatus.SUCCESS -> configuration.postEntityList
                DatabaseDataSourceStatus.NO_DATA -> listOf()
            }

        override fun savePosts(posts: List<PostEntity>) = Unit

        override fun getCommentsByPostId(postId: Int): List<CommentEntity> =
            when (status) {
                DatabaseDataSourceStatus.SUCCESS -> configuration.commentEntityList
                DatabaseDataSourceStatus.NO_DATA -> listOf()
            }

        override fun saveComments(comments: List<CommentEntity>) = Unit
    }
}