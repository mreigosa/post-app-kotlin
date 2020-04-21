package instrumentation.localdatasource

import com.mreigar.data.model.UserEntity
import instrumentation.data.DataEntityInstrument

data class UserDatabaseDataSourceConfiguration(
    var userEntityList: List<UserEntity> = DataEntityInstrument.givenUserEntityList(1),
    val userEntity: UserEntity? = null
)