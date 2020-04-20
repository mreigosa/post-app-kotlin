package com.mreigar.postapp.data.mapper

import com.mreigar.data.maper.UserMapper
import com.mreigar.domain.model.User
import instrumentation.data.DataEntityInstrument
import org.assertj.core.api.Assertions
import org.junit.Test

class UserMapperTest {

    @Test
    fun `given user data entity, map to domain is correct`() {
        val dataEntity = DataEntityInstrument.givenUserEntity()

        val mappedInstance: Any = UserMapper().mapFromEntity(dataEntity)

        Assertions.assertThat(mappedInstance is User).isTrue()
        Assertions.assertThat((mappedInstance as User).id).isEqualTo(dataEntity.id)
        Assertions.assertThat(mappedInstance.name).isEqualTo(dataEntity.name)
        Assertions.assertThat(mappedInstance.username).isEqualTo(dataEntity.username)
        Assertions.assertThat(mappedInstance.email).isEqualTo(dataEntity.email)
    }
}