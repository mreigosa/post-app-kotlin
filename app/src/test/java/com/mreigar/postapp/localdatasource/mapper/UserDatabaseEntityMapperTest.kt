package com.mreigar.postapp.localdatasource.mapper

import com.mreigar.data.model.UserEntity
import com.mreigar.localstorage.mapper.UserDatabaseEntityMapper
import com.mreigar.localstorage.model.UserDatabaseEntity
import instrumentation.data.DataEntityInstrument
import instrumentation.localdatasource.DatabaseEntityInstrument
import org.assertj.core.api.Assertions
import org.junit.Test

class UserDatabaseEntityMapperTest {

    @Test
    fun `that can map user from database entity`() {
        val databaseEntity = DatabaseEntityInstrument.givenUserDatabaseEntity()

        val mappedInstance: Any = UserDatabaseEntityMapper().mapFromDatabase(databaseEntity)

        Assertions.assertThat(mappedInstance is UserEntity).isTrue()
        Assertions.assertThat((mappedInstance as UserEntity).id).isEqualTo(databaseEntity.id)
        Assertions.assertThat(mappedInstance.name).isEqualTo(databaseEntity.name)
        Assertions.assertThat(mappedInstance.username).isEqualTo(databaseEntity.username)
        Assertions.assertThat(mappedInstance.email).isEqualTo(databaseEntity.email)
    }

    @Test
    fun `that can map user to database entity`() {
        val dataEntity = DataEntityInstrument.givenUserEntity()

        val mappedInstance: Any = UserDatabaseEntityMapper().mapToDatabase(dataEntity)

        Assertions.assertThat(mappedInstance is UserDatabaseEntity).isTrue()
        Assertions.assertThat((mappedInstance as UserDatabaseEntity).id).isEqualTo(dataEntity.id)
        Assertions.assertThat(mappedInstance.name).isEqualTo(dataEntity.name)
        Assertions.assertThat(mappedInstance.username).isEqualTo(dataEntity.username)
        Assertions.assertThat(mappedInstance.email).isEqualTo(dataEntity.email)
    }
}