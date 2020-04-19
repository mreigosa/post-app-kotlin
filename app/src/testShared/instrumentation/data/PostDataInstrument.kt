package instrumentation.data

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
}