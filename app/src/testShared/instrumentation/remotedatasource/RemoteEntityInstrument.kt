package instrumentation.remotedatasource

import com.mreigar.network.model.CommentRemoteEntity
import com.mreigar.network.model.PostRemoteEntity
import com.mreigar.network.model.UserRemoteEntity

object RemoteEntityInstrument {

    fun givenPostRemoteEntity() = PostRemoteEntity(1, 1, "title", "body")

    fun givenCommentRemoteEntity() = CommentRemoteEntity(1, 1, "name", "name@mail.com", "body")

    fun givenUserRemoteEntity() = UserRemoteEntity(1, "name", "username", "name@mail.com")
}