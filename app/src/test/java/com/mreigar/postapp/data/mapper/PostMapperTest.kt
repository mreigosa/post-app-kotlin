package com.mreigar.postapp.data.mapper

import com.mreigar.data.maper.PostMapper
import com.mreigar.data.model.PostEntity
import com.mreigar.domain.model.Post
import instrumentation.data.DataEntityInstrument
import instrumentation.domain.DomainEntityInstrument.givenPost
import org.assertj.core.api.Assertions
import org.junit.Test

class PostMapperTest {

    @Test
    fun `given a data entity, map to domain is correct`() {
        val dataEntity = DataEntityInstrument.givenPostEntity()

        val mappedInstance: Any = PostMapper().mapFromEntity(dataEntity)

        Assertions.assertThat(mappedInstance is Post).isTrue()
        Assertions.assertThat((mappedInstance as Post).id).isEqualTo(dataEntity.id)
        Assertions.assertThat(mappedInstance.userId).isEqualTo(dataEntity.userId)
        Assertions.assertThat(mappedInstance.title).isEqualTo(dataEntity.title)
        Assertions.assertThat(mappedInstance.body).isEqualTo(dataEntity.body)
    }

    @Test
    fun `given a domain entity, map to entity is correct`() {
        val domainEntity = givenPost()

        val mappedInstance: Any = PostMapper().mapToEntity(domainEntity)

        Assertions.assertThat(mappedInstance is PostEntity).isTrue()
        Assertions.assertThat((mappedInstance as PostEntity).id).isEqualTo(domainEntity.id)
        Assertions.assertThat(mappedInstance.userId).isEqualTo(domainEntity.userId)
        Assertions.assertThat(mappedInstance.title).isEqualTo(domainEntity.title)
        Assertions.assertThat(mappedInstance.body).isEqualTo(domainEntity.body)
    }

}