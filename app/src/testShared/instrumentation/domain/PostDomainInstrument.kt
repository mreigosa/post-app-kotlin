package instrumentation.domain

import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.Post
import java.util.*

object PostDomainInstrument {

    fun givenPost() = Post(1, 1, "title", "body")

    fun givenPostList(size: Int): List<Post> {
        val list: MutableList<Post> = LinkedList<Post>()
        for (i in 0 until size) {
            list.add(Post(i, i, "Title - $i", "Body - $i"))
        }

        return list
    }

    fun givenCommentList(size: Int): List<Comment> {
        val list: MutableList<Comment> = LinkedList<Comment>()
        for (i in 0 until size) {
            list.add(Comment(i, i, "name$i", "email$i", "body$i"))
        }

        return list
    }
}