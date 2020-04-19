package com.mreigar.postapp.remotedatasource

import com.mreigar.data.model.PostEntity
import com.mreigar.network.mapper.PostEntityMapper
import instrumentation.remotedatasource.PostRemoteInstrument.givenPostRemoteEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostEntityMapperTest {

    @Test
    fun `that can map a network response to entity`() {
        val remoteEntity = givenPostRemoteEntity()

        val mappedInstance: Any = PostEntityMapper().mapFromRemote(remoteEntity)

        assertThat(mappedInstance is PostEntity).isTrue()
        assertThat((mappedInstance as PostEntity).id).isEqualTo(remoteEntity.id)
        assertThat(mappedInstance.userId).isEqualTo(remoteEntity.userId)
        assertThat(mappedInstance.title).isEqualTo(remoteEntity.title)
        assertThat(mappedInstance.body).isEqualTo(remoteEntity.body)
    }
}