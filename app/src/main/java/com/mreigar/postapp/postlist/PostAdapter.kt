package com.mreigar.postapp.postlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mreigar.postapp.R
import com.mreigar.presentation.model.PostViewModel
import kotlinx.android.synthetic.main.layout_post_item.view.*

class PostAdapter(
    private val listener: (PostViewModel) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var items: MutableList<PostViewModel> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    fun setPosts(posts: List<PostViewModel>) {
        items.apply {
            clear()
            addAll(posts)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_post_item, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostViewModel, listener: (PostViewModel) -> Unit) = with(itemView) {
            postTitle.text = post.title
            postMessage.text = post.body

            setOnClickListener { listener.invoke(post) }
        }
    }
}