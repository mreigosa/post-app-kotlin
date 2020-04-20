package instrumentation.domain

import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.Post
import com.mreigar.domain.model.PostUser
import com.mreigar.domain.model.User
import java.util.*

object DomainEntityInstrument {

    fun givenPost(id: Int = 1, userId: Int = 1) = Post(userId, id, "title", "body")

    fun givenUser(id: Int = 1) = User(id, "name", "username", "name@mail.com")

    fun givenPostUser(postId: Int = 1, userId: Int = 1) = PostUser(givenPost(postId, userId), givenUser(userId))

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

    fun givenUserList(size: Int): List<User> {
        val list: MutableList<User> = LinkedList<User>()
        for (i in 0 until size) {
            list.add(User(i, "name$i", "username", "name@mail.com"))
        }

        return list
    }

    fun givenPostUserList(size: Int): List<PostUser> {
        val list: MutableList<PostUser> = LinkedList<PostUser>()
        for (i in 0 until size) {
            list.add(givenPostUser(i, i))
        }

        return list
    }
}