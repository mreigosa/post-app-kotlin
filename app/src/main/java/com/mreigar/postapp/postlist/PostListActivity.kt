package com.mreigar.postapp.postlist

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mreigar.postapp.R
import com.mreigar.postapp.extension.gone
import com.mreigar.postapp.extension.visible
import com.mreigar.postapp.postdetails.PostDetailsActivity
import com.mreigar.presentation.model.PostViewModel
import com.mreigar.presentation.viewmodel.PostListViewModel
import com.mreigar.presentation.viewmodel.PostListViewState
import kotlinx.android.synthetic.main.activity_post_list.*
import kotlinx.android.synthetic.main.layout_post_error.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostListActivity : AppCompatActivity() {

    private val viewModel: PostListViewModel by viewModel()

    private val postAdapter: PostAdapter by lazy { PostAdapter(::onPostSelected) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        postRecyclerView.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@PostListActivity)
        }

        postErrorLottieView.apply {
            setAnimation(R.raw.empty)
            repeatCount = ValueAnimator.INFINITE
        }

        postErrorTitle.text = getString(R.string.error_post_list_title)
        postErrorBody.text = getString(R.string.error_post_list_body)
        postErrorRefreshButton.setOnClickListener { viewModel.onRefreshSelected() }

        viewModel.postListViewState.observe(this) {
            when (it) {
                PostListViewState.Loading -> showLoader()
                PostListViewState.Error -> showError()
                is PostListViewState.Success -> showData(it.posts)
            }
        }

        viewModel.onDataRequested()
    }

    private fun showLoader() {
        postListLoader.visible()
        postErrorLayout.gone()
    }

    private fun hideLoader() {
        postListLoader.gone()
    }

    private fun showData(posts: List<PostViewModel>) {
        hideLoader()
        postAdapter.setPosts(posts)
    }

    private fun showError() {
        hideLoader()
        postErrorLottieView.playAnimation()
        postErrorLayout.visible()
    }

    private fun onPostSelected(post: PostViewModel) {
        startActivity(PostDetailsActivity.intent(this, post))
    }
}
