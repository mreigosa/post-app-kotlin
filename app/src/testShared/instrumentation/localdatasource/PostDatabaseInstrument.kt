package instrumentation.localdatasource

import com.mreigar.localstorage.model.PostDatabaseEntity

object PostDatabaseInstrument {

    fun givenPostDatabaseEntity() = PostDatabaseEntity(1, 1, "title", "body")
}