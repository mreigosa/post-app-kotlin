package com.mreigar.presentation.mapper

import com.mreigar.domain.model.User
import com.mreigar.presentation.model.UserViewModel

class UserViewModelMapper : Mapper<User, UserViewModel> {
    override fun mapToView(domainEntity: User): UserViewModel = with(domainEntity) {
        UserViewModel(name, username, email, avatarUrl.orEmpty())
    }
}