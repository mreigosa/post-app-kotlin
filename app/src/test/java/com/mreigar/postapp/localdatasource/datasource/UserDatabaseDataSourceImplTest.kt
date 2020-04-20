package com.mreigar.postapp.localdatasource.datasource

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.mreigar.localstorage.database.AppDatabase
import com.mreigar.localstorage.datasource.PostDatabaseDataSourceImpl
import com.mreigar.localstorage.datasource.UserDatabaseDataSourceImpl
import com.mreigar.postapp.AppRobolectricTestRunner
import instrumentation.data.DataEntityInstrument
import instrumentation.data.DataEntityInstrument.givenUserEntity
import instrumentation.localdatasource.DatabaseEntityInstrument
import org.assertj.core.api.Assertions
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module

class UserDatabaseDataSourceImplTest : AppRobolectricTestRunner() {

    private lateinit var database: AppDatabase

    override fun before() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java).allowMainThreadQueries().build()

        startKoin {
            modules(module {
                single(override = true) { database }
            })
        }
    }

    override fun after() {
        database.close()
    }

    @Test
    fun `user entity can be retrieved from database`() {
        val databaseEntity = DatabaseEntityInstrument.givenUserDatabaseEntity()

        database.userDao().insertUser(databaseEntity)

        val user = UserDatabaseDataSourceImpl().getUserById(userId = 1)
        Assertions.assertThat(user).isNotNull
        Assertions.assertThat(user!!.id).isEqualTo(databaseEntity.id)
        Assertions.assertThat(user.name).isEqualTo(databaseEntity.name)
        Assertions.assertThat(user.username).isEqualTo(databaseEntity.username)
        Assertions.assertThat(user.email).isEqualTo(databaseEntity.email)
    }

    @Test
    fun `user entity can be saved into database`() {
        val dataSource = UserDatabaseDataSourceImpl()

        dataSource.saveUsers(listOf(givenUserEntity(userId = 1), givenUserEntity(userId = 2)))

        val user = UserDatabaseDataSourceImpl().getUserById(userId = 1)
        Assertions.assertThat(user).isNotNull
        Assertions.assertThat(user!!.id).isEqualTo(1)
    }
}