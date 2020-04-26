package com.mreigar.domain.executor.usecase

import com.mreigar.domain.executor.Result
import com.mreigar.domain.executor.Success
import com.mreigar.domain.executor.UseCase
import com.mreigar.domain.executor.usecase.params.GetCommentsByPostUseCaseParams
import com.mreigar.domain.model.*
import com.mreigar.domain.repository.PostDetailsRepositoryContract
import com.mreigar.domain.repository.PostRepositoryContract

class GetCommentsByPostUseCase(
    private val postRepository: PostRepositoryContract,
    private val postDetailsRepository: PostDetailsRepositoryContract
) : UseCase<GetCommentsByPostUseCaseParams, List<Comment>>() {

    override suspend fun run(): Result<List<Comment>> {
        val commentsResult = postRepository.getCommentsByPostId(useCaseParams.postId)
        if (commentsResult is Success && useCaseParams.showComplementaryInfo) {
            val emojisResult = postDetailsRepository.getEmailEmojis()
            if (emojisResult is Success) {
                val commentsWithDetails = commentsResult.data.map {
                    it.copy(details = getCommentDetails(it.email, emojisResult.data))
                }
                return Success(commentsWithDetails, commentsResult.dataStatus)
            }
        }

        return commentsResult
    }

    private fun getCommentDetails(email: String, emailEmojis: List<EmailEmoji>): CommentDetails {
        val pattern = getEmailPatternFromEmail(email)
        return CommentDetails(
            emojis = if (pattern == EmailPattern.NO_VALID_PATTERN) null else emailEmojis.firstOrNull { it.email == pattern }?.emojis,
            avatarUrl = (postDetailsRepository.getAvatarUrl(email) as Success).data
        )
    }
}