package instrumentation.remotedatasource

import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import instrumentation.remotedatasource.configuration.PostRemoteDataSourceConfiguration

object PostRemoteDataSourceInstrument {

    fun givenPostRemoteDataSource(
        status: RemoteDataSourceStatus = RemoteDataSourceStatus.SUCCESS,
        configuration: PostRemoteDataSourceConfiguration = PostRemoteDataSourceConfiguration()
    ): PostRemoteDataSourceContract = object : PostRemoteDataSourceContract {
        override fun getPosts(): List<PostEntity> =
            when (status) {
                RemoteDataSourceStatus.SUCCESS -> configuration.postEntityList
                RemoteDataSourceStatus.ERROR -> throw Exception()
            }

        override fun getComments(): List<CommentEntity> =
            when (status) {
                RemoteDataSourceStatus.SUCCESS -> configuration.commentEntityList
                RemoteDataSourceStatus.ERROR -> throw Exception()
            }
    }

}