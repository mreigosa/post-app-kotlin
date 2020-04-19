package instrumentation.remotedatasource

import com.mreigar.data.datasource.PostRemoteDataSourceContract
import com.mreigar.data.model.PostEntity

enum class PostDataSourceStatus {
    SUCCESS, ERROR
}

object PostRemoteDataSourceInstrument {

    fun givenPostRemoteDataSource(
        status: PostDataSourceStatus = PostDataSourceStatus.SUCCESS,
        configuration: PostRemoteDataSourceConfiguration = PostRemoteDataSourceConfiguration()
    ): PostRemoteDataSourceContract = object : PostRemoteDataSourceContract {
        override fun getPosts(): List<PostEntity> =
            when (status) {
                PostDataSourceStatus.SUCCESS -> configuration.postEntityList
                PostDataSourceStatus.ERROR -> throw Exception()
            }
    }

}