package com.mreigar.postapp.localdatasource

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.mreigar.localstorage.database.AppDatabase
import com.mreigar.localstorage.datasource.PostDatabaseDataSourceImpl
import com.mreigar.postapp.PostRobolectricTestRunner
import instrumentation.data.PostDataInstrument.givenPostEntityList
import instrumentation.localdatasource.PostDatabaseInstrument.givenPostDatabaseEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module

class PostDatabaseDataSourceImplTest : PostRobolectricTestRunner() {

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
    fun `post entity can be get from database`() {
        val databaseEntity = givenPostDatabaseEntity()

        database.postDao().insertPost(databaseEntity)

        val posts = PostDatabaseDataSourceImpl().getPosts()
        assertThat(posts.isEmpty()).isFalse()
        assertThat(posts[0].id).isEqualTo(databaseEntity.id)
        assertThat(posts[0].userId).isEqualTo(databaseEntity.userId)
        assertThat(posts[0].title).isEqualTo(databaseEntity.title)
        assertThat(posts[0].body).isEqualTo(databaseEntity.body)
    }

    @Test
    fun `post entity can be saved into database`() {
        val dataSource = PostDatabaseDataSourceImpl()

        dataSource.savePosts(givenPostEntityList(2))

        assertThat(dataSource.getPosts()).isNotEmpty
        assertThat(dataSource.getPosts().size).isEqualTo(2)
    }
}