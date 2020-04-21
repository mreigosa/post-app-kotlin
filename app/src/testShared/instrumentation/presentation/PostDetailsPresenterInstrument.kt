package instrumentation.presentation

import com.mreigar.domain.model.Comment
import com.mreigar.domain.model.User
import com.mreigar.presentation.model.CommentViewModel
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.model.UserViewModel
import com.mreigar.presentation.presenter.PostDetailsPresenter
import com.mreigar.presentation.presenter.PostDetailsViewTranslator
import instrumentation.data.RepositoryStatus
import instrumentation.domain.usecase.GetCommentsByPostUseCaseInstrument.givenGetCommentsByPostUseCase
import instrumentation.domain.usecase.GetUserByPostUseCaseInstrument.givenGetUserByPostUseCase
import instrumentation.presentation.PresentationEntityInstrument.givenPostViewModel

object PostDetailsPresenterInstrument {

    fun givenPostDetailsPresenter(
        callbackResult: PostDetailsCallbackResult,
        postFromArgs: PostViewModel? = givenPostViewModel(),
        getUserIsSuccess: Boolean = true,
        getCommentsIsSuccess: Boolean = true,
        user: User? = null,
        commentList: List<Comment>? = null
    ) = PostDetailsPresenter(
        givenPostDetailsViewTranslator(callbackResult, postFromArgs),
        givenGetUserByPostUseCase(
            repositoryStatus = if (getUserIsSuccess) RepositoryStatus.SUCCESS else RepositoryStatus.ERROR,
            user = user
        ),
        givenGetCommentsByPostUseCase(
            repositoryStatus = if (getCommentsIsSuccess) RepositoryStatus.SUCCESS else RepositoryStatus.ERROR,
            commentList = commentList
        )
    )

    private fun givenPostDetailsViewTranslator(callbackResult: PostDetailsCallbackResult, post: PostViewModel?) =
        object : PostDetailsViewTranslator {
            override fun getPostFromArgs(): PostViewModel? {
                callbackResult.putMethodCall(PostDetailsViewMethod.GET_POST_FROM_ARGS)
                return post
            }

            override fun closeScreen() {
                callbackResult.putMethodCall(PostDetailsViewMethod.CLOSE_SCREEN)
            }

            override fun showLoader() {
                callbackResult.putMethodCall(PostDetailsViewMethod.SHOW_LOADER)
            }

            override fun hideLoader() {
                callbackResult.putMethodCall(PostDetailsViewMethod.HIDE_LOADER)
            }

            override fun showError() {
                callbackResult.putMethodCall(PostDetailsViewMethod.SHOW_ERROR)
            }

            override fun showPostInfo(post: PostViewModel) {
                callbackResult.putMethodCall(PostDetailsViewMethod.SHOW_POST_INFO)
            }

            override fun showUserInfo(user: UserViewModel) {
                callbackResult.putMethodCall(PostDetailsViewMethod.SHOW_USER_INFO)
            }

            override fun showComments(comments: List<CommentViewModel>) {
                callbackResult.putMethodCall(PostDetailsViewMethod.SHOW_COMMENTS)
            }
        }
}

class PostDetailsCallbackResult : BaseCallbackResult<PostDetailsViewMethod>()

enum class PostDetailsViewMethod {
    GET_POST_FROM_ARGS,
    CLOSE_SCREEN,
    SHOW_LOADER,
    HIDE_LOADER,
    SHOW_ERROR,
    SHOW_POST_INFO,
    SHOW_USER_INFO,
    SHOW_COMMENTS
}