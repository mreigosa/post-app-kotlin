package instrumentation.remotedatasource

import com.mreigar.data.model.UserEntity
import instrumentation.data.DataEntityInstrument

data class UserRemoteDataSourceConfiguration(
    var userEntityList: List<UserEntity> = DataEntityInstrument.givenUserEntityList(1)
)