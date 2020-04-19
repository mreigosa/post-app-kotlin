package instrumentation.remotedatasource

import com.mreigar.data.model.PostEntity
import java.util.*

data class PostRemoteDataSourceConfiguration(
    var postEntityList: List<PostEntity> = givenPostEntityList(1)
)

fun givenPostEntityList(size: Int): List<PostEntity> {
    val list: MutableList<PostEntity> = LinkedList<PostEntity>()
    for (i in 0 until size) {
        list.add(PostEntity(i, i, "Title - $i", "Body - $i"))
    }

    return list
}