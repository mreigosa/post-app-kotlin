package com.mreigar.domain.executor.usecase

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.Error
import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.executor.usecase.params.GetCommentsByPostUseCaseParams
import com.mreigar.domain.model.*
import com.mreigar.domain.repository.ComplementaryDetailsRepositoryContract
import com.mreigar.domain.repository.PostRepositoryContract

class GetCommentsByPostUseCase(
    private val postRepository: PostRepositoryContract,
    private val complementaryDetailsRepository: ComplementaryDetailsRepositoryContract,
    dispatcherProvider: DispatcherProvider
) : UseCase<GetCommentsByPostUseCaseParams, List<Comment>>(dispatcherProvider) {

    override suspend fun run(params: GetCommentsByPostUseCaseParams?): Result<List<Comment>> {
        if (params == null) return Error()

        val commentsResult = postRepository.getCommentsByPostId(params.postId)

        if (commentsResult is Success && params.showComplementaryInfo) {
            val extraDetailsResult = complementaryDetailsRepository.getEmailEmojis()
            if (extraDetailsResult is Success) {
                val commentsWithDetails = commentsResult.data.map {
                    it.copy(details = getCommentDetails(it.email, extraDetailsResult.data))
                }
                return Success(commentsWithDetails, commentsResult.dataStatus)
            }
        }

        return commentsResult
    }

    private fun getCommentDetails(email: String, emailEmojis: List<EmailEmoji>): CommentDetails? {
        val pattern = getEmailPatternFromEmail(email)
        return if (pattern == EmailPattern.NO_VALID_PATTERN) {
            null
        } else {
            emailEmojis.firstOrNull { it.email == pattern }?.let { CommentDetails(it.emojis) }
        }
    }
}