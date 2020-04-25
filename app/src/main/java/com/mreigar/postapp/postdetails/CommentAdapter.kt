package com.mreigar.postapp.postdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mreigar.postapp.R
import com.mreigar.presentation.model.CommentViewModel
import kotlinx.android.synthetic.main.layout_comment_item.view.*

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var items: MutableList<CommentViewModel> = mutableListOf()

    fun setComments(comments: List<CommentViewModel>) {
        items.apply {
            clear()
            addAll(comments)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_comment_item, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: CommentViewModel) = with(itemView) {
            itemCommentEmail.text = "${comment.email} ${comment.emailEmojis}"
            itemCommentTitle.text = comment.name
            itemCommentMessage.text = comment.body
            Glide.with(this).load(comment.avatarUrl).apply(RequestOptions.circleCropTransform()).into(itemCommentAvatar)
        }
    }
}