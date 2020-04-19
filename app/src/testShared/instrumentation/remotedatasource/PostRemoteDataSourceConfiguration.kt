package instrumentation.remotedatasource

import com.mreigar.data.model.PostEntity
import instrumentation.data.PostRepositoryInstrument.givenPostEntity

data class PostRemoteDataSourceConfiguration(
    var postEntityList: List<PostEntity> = listOf(givenPostEntity())
)