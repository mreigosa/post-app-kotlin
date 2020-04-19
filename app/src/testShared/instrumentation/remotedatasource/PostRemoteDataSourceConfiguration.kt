package instrumentation.remotedatasource

import com.mreigar.data.model.PostEntity
import instrumentation.data.PostDataInstrument.givenPostEntityList
import java.util.*

data class PostRemoteDataSourceConfiguration(
    var postEntityList: List<PostEntity> = givenPostEntityList(1)
)