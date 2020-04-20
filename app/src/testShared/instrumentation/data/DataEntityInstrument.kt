package instrumentation.data

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import com.mreigar.data.model.PostUserEntity
import com.mreigar.data.model.UserEntity
import java.util.*

object DataEntityInstrument {

    fun givenPostEntity(id: Int = 1, userId: Int = 1) = PostEntity(userId, id, "title", "body")

    fun givenCommentEntity(id: Int = 1, postId: Int = 1) = CommentEntity(id, postId, "name", "name@mail.com", "body")

    fun givenUserEntity(id: Int = 1) = UserEntity(id, "name", "username", "name@mail.com")

    fun givenPostUserEntity(postId: Int = 1, userId: Int = 1) = PostUserEntity(givenPostEntity(postId, userId), givenUserEntity(userId))

    fun givenPostEntityList(size: Int): List<PostEntity> {
        val list: MutableList<PostEntity> = LinkedList<PostEntity>()
        for (i in 0 until size) {
            list.add(PostEntity(i, i, "Title - $i", "Body - $i"))
        }

        return list
    }

    fun givenCommentEntityList(size: Int): List<CommentEntity> {
        val list: MutableList<CommentEntity> = LinkedList<CommentEntity>()
        for (i in 0 until size) {
            list.add(CommentEntity(i, i, "name$i", "email&i", "body$i"))
        }

        return list
    }

    fun givenPostUserEntityList(size: Int): List<PostUserEntity> {
        val list: MutableList<PostUserEntity> = LinkedList<PostUserEntity>()
        for (i in 0 until size) {
            list.add(givenPostUserEntity(i, i))
        }

        return list
    }
}