package instrumentation.data

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.EmailEmojiEntity
import com.mreigar.data.model.PostEntity
import com.mreigar.data.model.UserEntity
import com.mreigar.domain.model.EmailPattern
import java.util.*

object DataEntityInstrument {

    fun givenPostEntity(id: Int = 1, userId: Int = 1) = PostEntity(id = id, userId = userId, title = "title", body = "body")

    fun givenCommentEntity(id: Int = 1, postId: Int = 1, email: String = "name@mail.com") = CommentEntity(id, postId, "name", email, "body")

    fun givenUserEntity(userId: Int = 1) = UserEntity(userId, "name", "username", "name@mail.com")

    fun givenEmailEmojiEntity(pattern: EmailPattern = EmailPattern.INFO, emojis: String = "\uD83D\uDD25") = EmailEmojiEntity(pattern, emojis)

    fun givenPostEntityList(size: Int): List<PostEntity> {
        val list: MutableList<PostEntity> = LinkedList<PostEntity>()
        for (i in 0 until size) {
            list.add(PostEntity(i, i, "Title - $i", "Body - $i"))
        }

        return list
    }

    fun givenCommentEntityList(size: Int, postId: Int? = null): List<CommentEntity> {
        val list: MutableList<CommentEntity> = LinkedList<CommentEntity>()
        for (i in 0 until size) {
            list.add(CommentEntity(i, postId ?: i, "name$i", "email&i", "body$i"))
        }

        return list
    }

    fun givenUserEntityList(size: Int): List<UserEntity> {
        val list: MutableList<UserEntity> = LinkedList<UserEntity>()
        for (i in 0 until size) {
            list.add(UserEntity(i, "name$i", "username$i", "name@mail.com"))
        }

        return list
    }
}