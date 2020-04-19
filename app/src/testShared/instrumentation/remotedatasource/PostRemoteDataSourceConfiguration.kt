package instrumentation.remotedatasource

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import instrumentation.data.PostDataInstrument
import instrumentation.data.PostDataInstrument.givenPostEntityList

data class PostRemoteDataSourceConfiguration(
    var postEntityList: List<PostEntity> = givenPostEntityList(1),
    var commentEntityList: List<CommentEntity> = PostDataInstrument.givenCommentEntityList(1)
)