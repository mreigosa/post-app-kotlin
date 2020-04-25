package instrumentation.localdatasource

import com.mreigar.data.datasource.UserDatabaseDataSourceContract
import com.mreigar.data.model.UserEntity
import instrumentation.localdatasource.configuration.UserDatabaseDataSourceConfiguration

object UserDatabaseDataSourceInstrument {

    fun givenUserDatabaseDataSource(
        status: DatabaseDataSourceStatus = DatabaseDataSourceStatus.SUCCESS,
        configuration: UserDatabaseDataSourceConfiguration = UserDatabaseDataSourceConfiguration()
    ): UserDatabaseDataSourceContract = object : UserDatabaseDataSourceContract {

        override fun saveUsers(users: List<UserEntity>) {
            configuration.userEntityList = users
        }

        override fun getUserById(userId: Int): UserEntity? =
            when (status) {
                DatabaseDataSourceStatus.SUCCESS -> configuration.userEntity
                DatabaseDataSourceStatus.NO_DATA -> null
            }
    }
}