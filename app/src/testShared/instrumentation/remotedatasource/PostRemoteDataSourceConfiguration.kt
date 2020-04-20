package instrumentation.remotedatasource

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import instrumentation.data.DataEntityInstrument

data class PostRemoteDataSourceConfiguration(
    var postEntityList: List<PostEntity> = DataEntityInstrument.givenPostEntityList(1),
    var commentEntityList: List<CommentEntity> = DataEntityInstrument.givenCommentEntityList(1)
)