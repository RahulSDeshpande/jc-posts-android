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
        binding.apply {

            this.postEntity = postEntity
            this.postEventListener = postEventListener

            clPost.setOnClickListener {
                postEventListener?.onListItemClicked(
                    listPosition = adapterPosition,
                    postEntity = postEntity
                )
            }

            // TODO | THIS IS THE WORK AROUND, BCOZ 'app:onCheckChanged' IS WIP
            cbPostIsFavourite.apply {

                setOnCheckedChangeListener(null)

                isChecked = postEntity.isFavourite

                setOnCheckedChangeListener { _, isChecked ->

                    postEventListener?.onFavouriteIconClicked(
                        listPosition = adapterPosition,
                        postEntity = postEntity.apply {
                            isFavourite = isChecked
                            favouritedTime =
                                if (isChecked) System.currentTimeMillis()
                                else null
                        }
                    )
                }
            }

            executePendingBindings()
        }
    }
}