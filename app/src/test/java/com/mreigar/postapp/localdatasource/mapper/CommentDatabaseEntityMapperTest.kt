package com.mreigar.postapp.localdatasource.mapper

import com.mreigar.data.model.CommentEntity
import com.mreigar.localstorage.mapper.CommentDatabaseEntityMapper
import com.mreigar.localstorage.model.CommentDatabaseEntity
import instrumentation.data.DataEntityInstrument
import instrumentation.localdatasource.DatabaseEntityInstrument
import org.assertj.core.api.Assertions
import org.junit.Test

class CommentDatabaseEntityMapperText {

    @Test
    fun `that can map comment from database entity`() {
        val databaseEntity = DatabaseEntityInstrument.givenCommentDatabaseEntity()

        val mappedInstance: Any = CommentDatabaseEntityMapper().mapFromDatabase(databaseEntity)

        Assertions.assertThat(mappedInstance is CommentEntity).isTrue()
        Assertions.assertThat((mappedInstance as CommentEntity).id).isEqualTo(databaseEntity.id)
        Assertions.assertThat(mappedInstance.postId).isEqualTo(databaseEntity.postId)
        Assertions.assertThat(mappedInstance.name).isEqualTo(databaseEntity.name)
        Assertions.assertThat(mappedInstance.email).isEqualTo(databaseEntity.email)
        Assertions.assertThat(mappedInstance.body).isEqualTo(databaseEntity.body)
    }

    @Test
    fun `that can map comment to database entity`() {
        val dataEntity = DataEntityInstrument.givenCommentEntity()

        val mappedInstance: Any = CommentDatabaseEntityMapper().mapToDatabase(dataEntity)

        Assertions.assertThat(mappedInstance is CommentDatabaseEntity).isTrue()
        Assertions.assertThat((mappedInstance as CommentDatabaseEntity).id).isEqualTo(dataEntity.id)
        Assertions.assertThat(mappedInstance.postId).isEqualTo(dataEntity.postId)
        Assertions.assertThat(mappedInstance.name).isEqualTo(dataEntity.name)
        Assertions.assertThat(mappedInstance.email).isEqualTo(dataEntity.email)
        Assertions.assertThat(mappedInstance.body).isEqualTo(dataEntity.body)
    }
}