package com.mreigar.postapp.data.mapper

import com.mreigar.data.maper.CommentMapper
import com.mreigar.domain.model.Comment
import instrumentation.data.DataEntityInstrument
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CommentMapperTest {

    @Test
    fun `given comment data entity, map to domain is correct`() {
        val dataEntity = DataEntityInstrument.givenCommentEntity()

        val mappedInstance: Any = CommentMapper().mapFromEntity(dataEntity)

        assertThat(mappedInstance is Comment).isTrue()
        assertThat((mappedInstance as Comment).id).isEqualTo(dataEntity.id)
        assertThat(mappedInstance.postId).isEqualTo(dataEntity.postId)
        assertThat(mappedInstance.name).isEqualTo(dataEntity.name)
        assertThat(mappedInstance.email).isEqualTo(dataEntity.email)
        assertThat(mappedInstance.body).isEqualTo(dataEntity.body)
    }
}