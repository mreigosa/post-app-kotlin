package com.mreigar.domain.executor.usecase.params

data class GetCommentsByPostUseCaseParams(
    val postId: Int,
    val showComplementaryInfo: Boolean
)