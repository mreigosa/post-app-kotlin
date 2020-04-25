package instrumentation.remotedatasource

import com.mreigar.data.datasource.UserRemoteDataSourceContract
import com.mreigar.data.model.UserEntity
import instrumentation.remotedatasource.configuration.UserRemoteDataSourceConfiguration

object UserRemoteDataSourceInstrument {

    fun givenUserRemoteDataSource(
        status: RemoteDataSourceStatus = RemoteDataSourceStatus.SUCCESS,
        configuration: UserRemoteDataSourceConfiguration = UserRemoteDataSourceConfiguration()
    ): UserRemoteDataSourceContract = object : UserRemoteDataSourceContract {
        override fun getUsers(): List<UserEntity> = when (status) {
            RemoteDataSourceStatus.SUCCESS -> configuration.userEntityList
            RemoteDataSourceStatus.ERROR -> throw Exception()
        }
    }
}