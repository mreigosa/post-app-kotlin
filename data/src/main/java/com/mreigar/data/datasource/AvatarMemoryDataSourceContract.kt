package com.mreigar.data.datasource

interface AvatarMemoryDataSourceContract {
    fun getAvatarUrl(email: String): String
}