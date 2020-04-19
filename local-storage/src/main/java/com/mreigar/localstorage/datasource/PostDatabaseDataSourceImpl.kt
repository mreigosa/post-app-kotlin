package com.mreigar.localstorage.datasource

import com.mreigar.data.datasource.PostDatabaseDataSourceContract
import com.mreigar.data.model.PostEntity
import com.mreigar.localstorage.database.AppDatabase
import com.mreigar.localstorage.mapper.PostEntityMapper
import org.koin.core.KoinComponent
import org.koin.core.inject

class PostDatabaseDataSourceImpl : PostDatabaseDataSourceContract, KoinComponent {

    private val database: AppDatabase by inject()
    private val mapper = PostEntityMapper()

    override fun getPosts(): List<PostEntity> {
        val data = database.postDao().getAllPosts()
        return data.map { mapper.mapFromDatabase(it) }
    }

    override fun savePosts(posts: List<PostEntity>) {
        database.postDao().insertPosts(posts.map { mapper.mapToDatabase(it) })
    }
}