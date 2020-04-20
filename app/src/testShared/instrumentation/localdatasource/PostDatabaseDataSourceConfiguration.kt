package instrumentation.localdatasource

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import instrumentation.data.DataEntityInstrument

data class PostDatabaseDataSourceConfiguration(
    var postEntityList: List<PostEntity> = DataEntityInstrument.givenPostEntityList(1),
    var commentEntityList: List<CommentEntity> = DataEntityInstrument.givenCommentEntityList(1)
)