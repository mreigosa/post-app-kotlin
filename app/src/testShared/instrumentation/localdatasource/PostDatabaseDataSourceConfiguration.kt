package instrumentation.localdatasource

import com.mreigar.data.model.PostEntity
import instrumentation.data.PostDataInstrument.givenPostEntityList

data class PostDatabaseDataSourceConfiguration(
    var postEntityList: List<PostEntity> = givenPostEntityList(1)
)