package com.rahulografy.jcposts.ui.main.postdetail.comments.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.databinding.ItemCommentBinding

class CommentViewHolder(private val binding: ItemCommentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(commentEntity: CommentEntity) {
        binding.apply {
            this.commentEntity = commentEntity
            executePendingBindings()
        }
    }
}