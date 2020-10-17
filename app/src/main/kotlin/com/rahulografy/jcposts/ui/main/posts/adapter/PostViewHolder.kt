package com.rahulografy.jcposts.ui.main.posts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.databinding.ItemPostBinding
import com.rahulografy.jcposts.ui.main.posts.listener.PostEventListener

class PostViewHolder(private val binding: ItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        postEntity: PostEntity,
        postEventListener: PostEventListener?
    ) {
        with(receiver = binding)
        {
            this.postEntity = postEntity
            this.postEventListener = postEventListener

            executePendingBindings()

            // TODO | THIS IS THE WORK AROUND, BCOZ 'app:onCheckChanged' IS WIP
            binding.cbIsFavourite.apply {
                setOnCheckedChangeListener(null)
                isChecked = postEntity.isFavourite
                setOnCheckedChangeListener { _, isChecked ->
                    postEventListener?.onFavouriteIconClicked(
                        listPosition = adapterPosition,
                        postEntity = postEntity.apply { isFavourite = isChecked }
                    )
                }
            }
        }
    }
}