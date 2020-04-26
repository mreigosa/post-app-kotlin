package com.mreigar.postapp.postlist

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mreigar.postapp.BaseActivity
import com.mreigar.postapp.R
import com.mreigar.postapp.extension.gone
import com.mreigar.postapp.extension.visible
import com.mreigar.postapp.injection.injectActivity
import com.mreigar.postapp.postdetails.PostDetailsActivity
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.presenter.PostListPresenter
import com.mreigar.presentation.presenter.PostListViewTranslator
import kotlinx.android.synthetic.main.activity_post_list.*
import kotlinx.android.synthetic.main.layout_empty_post_list.*

class PostListActivity : BaseActivity<PostListPresenter>(), PostListViewTranslator {

    override val presenter: PostListPresenter by injectActivity()

    private val postAdapter = PostAdapter(presenter::onPostClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        postRecyclerView.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@PostListActivity)
        }

        postListRefreshButton.setOnClickListener { presenter.onRefreshClicked() }
    }

    override fun showLoader() {
        postListLoader.visible()
        postListEmptyLayout.gone()
    }

    override fun hideLoader() {
        postListLoader.gone()
    }

    override fun showData(posts: List<PostViewModel>) {
        hideLoader()
        postAdapter.setPosts(posts)
    }

    override fun showError() {
        hideLoader()
        postListLottieView.apply {
            setAnimation(R.raw.empty)
            repeatCount = ValueAnimator.INFINITE
            playAnimation()
        }
        postListEmptyLayout.visible()
    }

    override fun showPostDetails(post: PostViewModel) {
        startActivity(PostDetailsActivity.intent(this, post))
    }

    override fun hideError() {
        postListEmptyLayout.gone()
    }
}