package com.mreigar.postapp.remotedatasource.mapper

import com.mreigar.data.model.UserEntity
import com.mreigar.network.mapper.UserRemoteEntityMapper
import instrumentation.remotedatasource.RemoteEntityInstrument
import org.assertj.core.api.Assertions
import org.junit.Test

class UserRemoteEntityMapperTest {

    @Test
    fun `that can map a remote user entity to data entity`() {
        val remoteEntity = RemoteEntityInstrument.givenUserRemoteEntity()

        val mappedInstance: Any = UserRemoteEntityMapper().mapFromRemote(remoteEntity)

        Assertions.assertThat(mappedInstance is UserEntity).isTrue()
        Assertions.assertThat((mappedInstance as UserEntity).id).isEqualTo(remoteEntity.id)
        Assertions.assertThat(mappedInstance.name).isEqualTo(remoteEntity.name)
        Assertions.assertThat(mappedInstance.username).isEqualTo(remoteEntity.username)
        Assertions.assertThat(mappedInstance.email).isEqualTo(remoteEntity.email)
    }
}