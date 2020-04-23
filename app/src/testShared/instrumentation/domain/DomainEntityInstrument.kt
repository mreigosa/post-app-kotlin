package instrumentation.domain

import com.mreigar.domain.model.*
import java.util.*

object DomainEntityInstrument {

    fun givenPost() = Post(1, 1, "title", "body")

    fun givenUser(userId: Int = 1) = User(userId, "name", "username", "name@mail.com")

    fun givenComment(id: Int = 1, postId: Int = 1, email: String = "name@mail.com") = Comment(id, postId, "name", email, "body", null)

    fun givenEmailEmoji(pattern: EmailPattern = EmailPattern.INFO, emojis: String = "fake-emojis") = EmailEmoji(pattern, emojis)

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
            list.add(Comment(i, i, "name$i", "email$i", "body$i", null))
        }

        return list
    }
}