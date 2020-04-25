package com.mreigar.postapp.localdatasource.datasource

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.mreigar.localstorage.database.AppDatabase
import com.mreigar.localstorage.datasource.PostDatabaseDataSourceImpl
import com.mreigar.postapp.AppRobolectricTestRunner
import instrumentation.data.DataEntityInstrument
import instrumentation.data.DataEntityInstrument.givenCommentEntity
import instrumentation.localdatasource.DatabaseEntityInstrument
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module

class PostDatabaseDataSourceImplTest : AppRobolectricTestRunner() {

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
    fun `post entity can be gotten from database`() {
        val databaseEntity = DatabaseEntityInstrument.givenPostDatabaseEntity()

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

        dataSource.savePosts(DataEntityInstrument.givenPostEntityList(2))

        assertThat(dataSource.getPosts()).isNotEmpty
        assertThat(dataSource.getPosts().size).isEqualTo(2)
    }

    @Test
    fun `comment entity can be gotten from database`() {
        val databaseEntity = DatabaseEntityInstrument.givenCommentDatabaseEntity(postId = 1)

        database.commentDao().insertComment(databaseEntity)

        val comments = PostDatabaseDataSourceImpl().getCommentsByPostId(1)
        assertThat(comments.isEmpty()).isFalse()
        assertThat(comments[0].id).isEqualTo(databaseEntity.id)
        assertThat(comments[0].name).isEqualTo(databaseEntity.name)
        assertThat(comments[0].email).isEqualTo(databaseEntity.email)
        assertThat(comments[0].body).isEqualTo(databaseEntity.body)
    }

    @Test
    fun `comment entity can be saved into database`() {
        val dataSource = PostDatabaseDataSourceImpl()

        val commentList = listOf(
            givenCommentEntity(id = 1, postId = 1),
            givenCommentEntity(id = 3, postId = 2),
            givenCommentEntity(id = 2, postId = 1)
        )

        dataSource.saveComments(commentList)

        val comments = PostDatabaseDataSourceImpl().getCommentsByPostId(1)
        assertThat(comments).isNotEmpty
        assertThat(comments.size).isEqualTo(2)
    }
}