package com.mreigar.postapp.postdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
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

class PostDetailsActivity : BaseActivity<PostDetailsPresenter>(), PostDetailsViewTranslator {

    companion object {
        private const val PARAMS_ARG = "postParams"

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

        postDetailsRecyclerView.apply {
            adapter = commentAdapter
            layoutManager = LinearLayoutManager(this@PostDetailsActivity)
            isNestedScrollingEnabled = false
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

    override fun getPostFromArgs(): PostViewModel? = (intent.extras?.get(PARAMS_ARG) as? PostViewModel)

    override fun closeScreen() = onBackPressed()

    override fun showLoader() {
        postDetailsLoader.visible()
    }

    override fun hideLoader() {
        postDetailsLoader.gone()
    }

    override fun showError() {
        hideLoader()
        Snackbar.make(findViewById(android.R.id.content), "Error loading post details", Snackbar.LENGTH_LONG).show()
    }

    override fun showPostInfo(post: PostViewModel) {
        postDetailsBody.text = post.body
    }

    override fun showUserInfo(user: UserViewModel) {
        postDetailsUserName.text = user.name
        postDetailsUserUsername.text = user.username
    }

    override fun showComments(comments: List<CommentViewModel>) {
        postDetailsCommentsTitle.text = "${comments.size} Comments _____________"
        commentAdapter.setComments(comments)
    }


}