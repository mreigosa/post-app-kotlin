package instrumentation.localdatasource

import com.mreigar.data.datasource.AvatarMemoryDataSourceContract
import instrumentation.localdatasource.configuration.AvatarMemoryDataSourceConfiguration

object AvatarMemoryDataSourceInstrument {

    fun givenAvatarMemoryDataSource(
        status: DatabaseDataSourceStatus = DatabaseDataSourceStatus.SUCCESS,
        configuration: AvatarMemoryDataSourceConfiguration = AvatarMemoryDataSourceConfiguration()
    ): AvatarMemoryDataSourceContract = object : AvatarMemoryDataSourceContract {
        override fun getAvatarUrl(email: String): String =
            when (status) {
                DatabaseDataSourceStatus.SUCCESS -> configuration.avatarUrl
                DatabaseDataSourceStatus.NO_DATA -> ""
            }
    }
}