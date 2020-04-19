package instrumentation.presentation

import com.mreigar.domain.model.Post
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.presenter.PostListPresenter
import com.mreigar.presentation.presenter.PostListViewTranslator
import instrumentation.data.PostRepositoryStatus
import instrumentation.domain.GetPostsUseCaseInstrument

object PostListPresenterInstrument {

    fun givenPostListPresenter(
        callbackResult: PostListCallbackResult,
        getPostsIsSuccess: Boolean = true,
        postList: List<Post>? = null
    ) = PostListPresenter(
        givenPostListViewTranslator(callbackResult),
        givenGetPostsUseCase(getPostsIsSuccess, postList)
    )

    private fun givenGetPostsUseCase(getPostsIsSuccess: Boolean, postList: List<Post>?) =
        GetPostsUseCaseInstrument.givenGetPostsUseCase(
            repositoryStatus = if (getPostsIsSuccess) PostRepositoryStatus.SUCCESS else PostRepositoryStatus.ERROR,
            postList = postList
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
    }
}

class PostListCallbackResult : BaseCallbackResult<PostListViewMethod>()

enum class PostListViewMethod {
    SHOW_LOADER,
    HIDE_LOADER,
    SHOW_DATA,
    SHOW_ERROR
}