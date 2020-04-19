package instrumentation.localdatasource

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import instrumentation.data.PostDataInstrument.givenCommentEntityList
import instrumentation.data.PostDataInstrument.givenPostEntityList

data class PostDatabaseDataSourceConfiguration(
    var postEntityList: List<PostEntity> = givenPostEntityList(1),
    var commentEntityList: List<CommentEntity> = givenCommentEntityList(1)
)