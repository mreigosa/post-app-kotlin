package com.mreigar.localstorage.datasource

import com.mreigar.data.datasource.AvatarMemoryDataSourceContract

class AvatarMemoryDataSourceImpl : AvatarMemoryDataSourceContract {

    companion object {
        private const val AVATAR_BASE_URL = "https://api.adorable.io/avatars/200/"
    }

    override fun getAvatarUrl(email: String): String = "$AVATAR_BASE_URL$email"
}