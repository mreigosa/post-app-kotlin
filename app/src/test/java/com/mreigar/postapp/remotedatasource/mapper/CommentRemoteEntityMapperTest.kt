package com.mreigar.postapp.remotedatasource.mapper

import com.mreigar.data.model.CommentEntity
import com.mreigar.network.mapper.CommentRemoteEntityMapper
import instrumentation.remotedatasource.RemoteEntityInstrument
import org.assertj.core.api.Assertions
import org.junit.Test

class CommentRemoteEntityMapperTest {

    @Test
    fun `that can map a remote comment entity to data entity`() {
        val remoteEntity = RemoteEntityInstrument.givenCommentRemoteEntity()

        val mappedInstance: Any = CommentRemoteEntityMapper().mapFromRemote(remoteEntity)

        Assertions.assertThat(mappedInstance is CommentEntity).isTrue()
        Assertions.assertThat((mappedInstance as CommentEntity).id).isEqualTo(remoteEntity.id)
        Assertions.assertThat(mappedInstance.postId).isEqualTo(remoteEntity.postId)
        Assertions.assertThat(mappedInstance.name).isEqualTo(remoteEntity.name)
        Assertions.assertThat(mappedInstance.email).isEqualTo(remoteEntity.email)
        Assertions.assertThat(mappedInstance.body).isEqualTo(remoteEntity.body)
    }
}