package com.mreigar.postapp.localdatasource.mapper

import com.mreigar.data.model.PostEntity
import com.mreigar.localstorage.mapper.PostDatabaseEntityMapper
import com.mreigar.localstorage.model.PostDatabaseEntity
import instrumentation.data.DataEntityInstrument
import instrumentation.localdatasource.DatabaseEntityInstrument
import org.assertj.core.api.Assertions
import org.junit.Test

class PostDatabaseEntityMapperTest {

    @Test
    fun `that can map post from database entity`() {
        val databaseEntity = DatabaseEntityInstrument.givenPostDatabaseEntity(id = 1, userId = 2)

        val mappedInstance: Any = PostDatabaseEntityMapper().mapFromDatabase(databaseEntity)

        Assertions.assertThat(mappedInstance is PostEntity).isTrue()
        Assertions.assertThat((mappedInstance as PostEntity).id).isEqualTo(databaseEntity.id)
        Assertions.assertThat(mappedInstance.userId).isEqualTo(databaseEntity.userId)
        Assertions.assertThat(mappedInstance.title).isEqualTo(databaseEntity.title)
        Assertions.assertThat(mappedInstance.body).isEqualTo(databaseEntity.body)
    }

    @Test
    fun `that can map post to database entity`() {
        val dataEntity = DataEntityInstrument.givenPostEntity()

        val mappedInstance: Any = PostDatabaseEntityMapper().mapToDatabase(dataEntity)

        Assertions.assertThat(mappedInstance is PostDatabaseEntity).isTrue()
        Assertions.assertThat((mappedInstance as PostDatabaseEntity).id).isEqualTo(dataEntity.id)
        Assertions.assertThat(mappedInstance.userId).isEqualTo(dataEntity.userId)
        Assertions.assertThat(mappedInstance.title).isEqualTo(dataEntity.title)
        Assertions.assertThat(mappedInstance.body).isEqualTo(dataEntity.body)
    }
}