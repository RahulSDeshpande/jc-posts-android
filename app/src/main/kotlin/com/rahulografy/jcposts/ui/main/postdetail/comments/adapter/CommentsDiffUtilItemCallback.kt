package com.rahulografy.jcposts.ui.main.postdetail.comments.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity

class CommentsDiffUtilItemCallback : DiffUtil.ItemCallback<CommentEntity>() {

    override fun areItemsTheSame(
        oldItem: CommentEntity,
        newItem: CommentEntity
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CommentEntity,
        newItem: CommentEntity
    ) = oldItem.id == newItem.id
}