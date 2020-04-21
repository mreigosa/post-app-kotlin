package instrumentation.localdatasource

import com.mreigar.localstorage.model.CommentDatabaseEntity
import com.mreigar.localstorage.model.PostDatabaseEntity
import com.mreigar.localstorage.model.UserDatabaseEntity

object DatabaseEntityInstrument {

    fun givenPostDatabaseEntity(id: Int = 1, userId: Int = 1) = PostDatabaseEntity(id = id, userId = userId, title = "title", body = "body")

    fun givenUserDatabaseEntity(id: Int = 1) = UserDatabaseEntity(id, "name", "username", "name@mail.com")

    fun givenCommentDatabaseEntity(postId: Int = 1) = CommentDatabaseEntity(id = 1, postId = postId, name = "name", email = "name@mail.com", body = "body")
}