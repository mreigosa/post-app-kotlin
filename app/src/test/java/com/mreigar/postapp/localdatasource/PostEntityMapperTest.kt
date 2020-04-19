package com.mreigar.postapp.localdatasource

import com.mreigar.data.model.PostEntity
import com.mreigar.localstorage.mapper.PostEntityMapper
import com.mreigar.localstorage.model.PostDatabaseEntity
import instrumentation.data.PostDataInstrument
import instrumentation.localdatasource.PostDatabaseInstrument
import org.assertj.core.api.Assertions
import org.junit.Test

class PostEntityMapperTest {

    @Test
    fun `that can map database data to entity`() {
        val databaseEntity = PostDatabaseInstrument.givenPostDatabaseEntity()

        val mappedInstance: Any = PostEntityMapper().mapFromDatabase(databaseEntity)

        Assertions.assertThat(mappedInstance is PostEntity).isTrue()
        Assertions.assertThat((mappedInstance as PostEntity).id).isEqualTo(databaseEntity.id)
        Assertions.assertThat(mappedInstance.userId).isEqualTo(databaseEntity.userId)
        Assertions.assertThat(mappedInstance.title).isEqualTo(databaseEntity.title)
        Assertions.assertThat(mappedInstance.body).isEqualTo(databaseEntity.body)
    }

    @Test
    fun `that can map data entity to database entity`() {
        val dataEntity = PostDataInstrument.givenPostEntity()

        val mappedInstance: Any = PostEntityMapper().mapToDatabase(dataEntity)

        Assertions.assertThat(mappedInstance is PostDatabaseEntity).isTrue()
        Assertions.assertThat((mappedInstance as PostDatabaseEntity).id).isEqualTo(dataEntity.id)
        Assertions.assertThat(mappedInstance.userId).isEqualTo(dataEntity.userId)
        Assertions.assertThat(mappedInstance.title).isEqualTo(dataEntity.title)
        Assertions.assertThat(mappedInstance.body).isEqualTo(dataEntity.body)
    }
}