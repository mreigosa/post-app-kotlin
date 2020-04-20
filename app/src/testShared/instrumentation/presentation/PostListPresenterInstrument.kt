package instrumentation.presentation

import com.mreigar.domain.model.PostUser
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.presenter.PostListPresenter
import com.mreigar.presentation.presenter.PostListViewTranslator
import instrumentation.data.RepositoryStatus
import instrumentation.domain.GetPostsUsersUseCaseInstrument

object PostListPresenterInstrument {

    fun givenPostListPresenter(
        callbackResult: PostListCallbackResult,
        refreshPostsIsSuccess: Boolean = true,
        refreshUserIsSuccess: Boolean = true,
        postUserList: List<PostUser>? = null
    ) = PostListPresenter(
        givenPostListViewTranslator(callbackResult),
        givenGetPostsUsersUseCase(refreshPostsIsSuccess, refreshUserIsSuccess, postUserList)
    )

    private fun givenGetPostsUsersUseCase(refreshPostsIsSuccess: Boolean, refreshUserIsSuccess: Boolean, postUserList: List<PostUser>?) =
        GetPostsUsersUseCaseInstrument.givenGetPostsUsersUseCase(
            postRepositoryStatus = if (refreshPostsIsSuccess) RepositoryStatus.SUCCESS else RepositoryStatus.ERROR,
            userRepositoryStatus = if (refreshUserIsSuccess) RepositoryStatus.SUCCESS else RepositoryStatus.ERROR,
            postUserList = postUserList
        )

    private fun givenPostListViewTranslator(callbackResult: PostListCallbackResult) = object : PostListViewTranslator {
        override fun showLoader() {
            callbackResult.putMethodCall(PostListViewMethod.SHOW_LOADER)
        }

        override fun hideLoader() {
            callbackResult.putMethodCall(PostListViewMethod.HIDE_LOADER)
        }

        override fun showData(posts: List<PostViewModel>) {
            callbackResult.putMethodCall(PostListViewMethod.SHOW_DATA, posts)
        }

        override fun showError() {
            callbackResult.putMethodCall(PostListViewMethod.SHOW_ERROR)
        }

        override fun showPostDetails(post: PostViewModel) {
            callbackResult.putMethodCall(PostListViewMethod.SHOW_POST_DETAILS, post)
        }
    }
}

class PostListCallbackResult : BaseCallbackResult<PostListViewMethod>()

enum class PostListViewMethod {
    SHOW_LOADER,
    HIDE_LOADER,
    SHOW_DATA,
    SHOW_ERROR,
    SHOW_POST_DETAILS
}