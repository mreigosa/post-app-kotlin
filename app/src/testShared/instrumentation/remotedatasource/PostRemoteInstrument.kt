package instrumentation.remotedatasource

import com.mreigar.network.model.PostRemoteEntity

object PostRemoteInstrument {

    fun givenPostRemoteEntity() = PostRemoteEntity(1, 1, "title", "body")
}