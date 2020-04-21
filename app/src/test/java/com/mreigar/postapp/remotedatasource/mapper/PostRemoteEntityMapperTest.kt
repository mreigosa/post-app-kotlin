package com.mreigar.postapp.remotedatasource.mapper

import com.mreigar.data.model.PostEntity
import com.mreigar.network.mapper.PostRemoteEntityMapper
import instrumentation.remotedatasource.RemoteEntityInstrument
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostRemoteEntityMapperTest {

    @Test
    fun `that can map a remote post entity to data entity`() {
        val remoteEntity = RemoteEntityInstrument.givenPostRemoteEntity(id = 1, userId = 2)

        val mappedInstance: Any = PostRemoteEntityMapper().mapFromRemote(remoteEntity)

        assertThat(mappedInstance is PostEntity).isTrue()
        assertThat((mappedInstance as PostEntity).id).isEqualTo(remoteEntity.id)
        assertThat(mappedInstance.userId).isEqualTo(remoteEntity.userId)
        assertThat(mappedInstance.title).isEqualTo(remoteEntity.title)
        assertThat(mappedInstance.body).isEqualTo(remoteEntity.body)
    }

}