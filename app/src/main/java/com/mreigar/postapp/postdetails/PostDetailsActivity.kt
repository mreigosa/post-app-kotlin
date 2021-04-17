package com.mreigar.postapp.postdetails

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mreigar.postapp.R
import com.mreigar.postapp.extension.gone
import com.mreigar.postapp.extension.visible
import com.mreigar.presentation.model.CommentViewModel
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.model.UserViewModel
import com.mreigar.presentation.viewmodel.PostCommentsViewState
import com.mreigar.presentation.viewmodel.PostDetailsViewModel
import com.mreigar.presentation.viewmodel.PostDetailsViewState
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.layout_post_error.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostDetailsActivity : AppCompatActivity() {

    companion object {
        const val PARAMS_ARG = "postParams"

        fun intent(context: Context, post: PostViewModel) =
                Intent(context, PostDetailsActivity::class.java).apply {
                    putExtra(PARAMS_ARG, post)
                }
    }

    private val viewModel: PostDetailsViewModel by viewModel()

    private val commentAdapter by lazy { CommentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        postDetailsRecyclerView.apply {
            adapter = commentAdapter
            layoutManager = LinearLayoutManager(this@PostDetailsActivity)
            isNestedScrollingEnabled = false
        }
        postDetailsEmptyCommentsRefresh.setOnClickListener { viewModel.onRefreshCommentsSelected() }

        postErrorLottieView.apply {
            setAnimation(R.raw.empty)
            repeatCount = ValueAnimator.INFINITE
        }

        postErrorTitle.text = getString(R.string.error_post_details_title)
        postErrorBody.text = getString(R.string.error_post_details_body)
        postErrorRefreshButton.setOnClickListener { viewModel.onRefreshSelected() }

        viewModel.postDetailsViewState.observe(this) {
            when (it) {
                PostDetailsViewState.Loading -> {
                    hideError()
                    showLoader()
                }
                PostDetailsViewState.Error -> showError()
                is PostDetailsViewState.UserRetrieved -> {
                    showPostInfo(it.post)
                    showUserInfo(it.user)
                }
            }
        }

        viewModel.commentsViewState.observe(this) {
            when (it) {
                PostCommentsViewState.Loading -> {
                    hideCommentsError()
                    showLoader()
                }
                PostCommentsViewState.Error -> {
                    hideLoader()
                    showCommentsError()
                }
                is PostCommentsViewState.CommentsRetrieved -> {
                    hideLoader()
                    showComments(it.comments)
                }
            }
        }

        getPostFromArgs()?.let {
            viewModel.onPostSelected(it)
        } ?: closeScreen()
    }

    private fun getPostFromArgs(): PostViewModel? = (intent.extras?.get(PARAMS_ARG) as? PostViewModel)

    private fun closeScreen() = onBackPressed()

    private fun showLoader() {
        postDetailsLoader.visible()
        postErrorLayout.gone()
    }

    private fun hideLoader() {
        postDetailsLoader.gone()
    }

    private fun showError() {
        hideLoader()
        postDetailsUserCard.gone()
        postErrorLottieView.playAnimation()
        postErrorLayout.visible()
    }

    private fun hideError() {
        postErrorLayout.gone()
    }

    private fun showPostInfo(post: PostViewModel) {
        postDetailsTitle.text = post.title
        postDetailsBody.text = post.body
        postDetailsUserCard.visible()
    }

    private fun showUserInfo(user: UserViewModel) {
        postDetailsUserName.text = user.name
        postDetailsUserUsername.text = user.username
        postDetailsUserEmail.text = user.email
        Glide.with(this)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(postDetailsUserAvatar)
    }

    private fun showComments(comments: List<CommentViewModel>) {
        postDetailsCommentsTitle.text = getString(R.string.comment_list_header).format(comments.size)
        commentAdapter.setComments(comments)
    }

    private fun showCommentsError() {
        postDetailsCommentsTitle.text = getString(R.string.comment_list_header).format("-")
        postDetailsEmptyCommentsText.visible()
        postDetailsEmptyCommentsRefresh.visible()
    }

    private fun hideCommentsError() {
        postDetailsEmptyCommentsText.gone()
        postDetailsEmptyCommentsRefresh.gone()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
