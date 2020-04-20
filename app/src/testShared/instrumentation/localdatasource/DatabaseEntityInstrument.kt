package instrumentation.localdatasource

import com.mreigar.localstorage.model.CommentDatabaseEntity
import com.mreigar.localstorage.model.PostDatabaseEntity
import com.mreigar.localstorage.model.UserDatabaseEntity

object DatabaseEntityInstrument {

    fun givenPostDatabaseEntity() = PostDatabaseEntity(1, 1, "title", "body")

    fun givenUserDatabaseEntity() = UserDatabaseEntity(1, "name", "username", "name@mail.com")

    fun givenCommentDatabaseEntity(postId: Int = 1) = CommentDatabaseEntity(1, postId, "name", "name@mail.com", "body")
}