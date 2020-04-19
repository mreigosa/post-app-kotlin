package instrumentation.data

import com.mreigar.data.model.CommentEntity
import com.mreigar.data.model.PostEntity
import java.util.*

object PostDataInstrument {

    fun givenPostEntity() = PostEntity(1, 1, "title", "body")

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
}