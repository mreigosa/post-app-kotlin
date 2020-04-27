package com.mreigar.postapp.postdetails

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mreigar.postapp.BaseActivity
import com.mreigar.postapp.R
import com.mreigar.postapp.extension.gone
import com.mreigar.postapp.extension.visible
import com.mreigar.postapp.injection.injectActivity
import com.mreigar.presentation.model.CommentViewModel
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.model.UserViewModel
import com.mreigar.presentation.presenter.PostDetailsPresenter
import com.mreigar.presentation.presenter.PostDetailsViewTranslator
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.layout_post_error.*

class PostDetailsActivity : BaseActivity<PostDetailsPresenter>(), PostDetailsViewTranslator {

    companion object {
        const val PARAMS_ARG = "postParams"

        fun intent(context: Context, post: PostViewModel) =
            Intent(context, PostDetailsActivity::class.java).apply {
                putExtra(PARAMS_ARG, post)
            }
    }

    override val presenter: PostDetailsPresenter by injectActivity()

    private val commentAdapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        postDetailsRecyclerView.apply {
            adapter = commentAdapter
            layoutManager = LinearLayoutManager(this@PostDetailsActivity)
            isNestedScrollingEnabled = false
        }
        postDetailsEmptyCommentsRefresh.setOnClickListener { presenter.onRefreshCommentsClicked() }

        postErrorLottieView.apply {
            setAnimation(R.raw.empty)
            repeatCount = ValueAnimator.INFINITE
        }

        postErrorTitle.text = getString(R.string.error_post_details_title)
        postErrorBody.text = getString(R.string.error_post_details_body)
        postErrorRefreshButton.setOnClickListener { presenter.onRefreshClicked() }
    }

    override fun getPostFromArgs(): PostViewModel? = (intent.extras?.get(PARAMS_ARG) as? PostViewModel)

    override fun closeScreen() = onBackPressed()

    override fun showLoader() {
        postDetailsLoader.visible()
        postErrorLayout.gone()
    }

    override fun hideLoader() {
        postDetailsLoader.gone()
    }

    override fun showError() {
        hideLoader()
        postDetailsUserCard.gone()
        postErrorLottieView.playAnimation()
        postErrorLayout.visible()
    }

    override fun hideError() {
        postErrorLayout.gone()
    }

    override fun showPostInfo(post: PostViewModel) {
        postDetailsTitle.text = post.title
        postDetailsBody.text = post.body
        postDetailsUserCard.visible()
    }

    override fun showUserInfo(user: UserViewModel) {
        postDetailsUserName.text = user.name
        postDetailsUserUsername.text = user.username
        postDetailsUserEmail.text = user.email
        Glide.with(this)
            .load(user.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(postDetailsUserAvatar)
    }

    override fun showComments(comments: List<CommentViewModel>) {
        postDetailsCommentsTitle.text = getString(R.string.comment_list_header).format(comments.size)
        commentAdapter.setComments(comments)
    }

    override fun showCommentsError() {
        postDetailsCommentsTitle.text = getString(R.string.comment_list_header).format("-")
        postDetailsEmptyCommentsText.visible()
        postDetailsEmptyCommentsRefresh.visible()
    }

    override fun hideCommentsError() {
        postDetailsEmptyCommentsText.gone()
        postDetailsEmptyCommentsRefresh.gone()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}