package com.rahulografy.jcposts.ui.main.posts.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity

class PostsDiffUtilItemCallback : DiffUtil.ItemCallback<PostEntity>() {

    override fun areItemsTheSame(
        oldItem: PostEntity,
        newItem: PostEntity
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: PostEntity,
        newItem: PostEntity
    ) = oldItem.id == newItem.id
}