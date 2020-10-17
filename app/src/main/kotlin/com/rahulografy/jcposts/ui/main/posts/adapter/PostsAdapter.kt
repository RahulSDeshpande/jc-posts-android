package com.rahulografy.jcposts.ui.main.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.ui.base.adapter.BaseListAdapter
import com.rahulografy.jcposts.ui.main.posts.listener.PostEventListener

class PostsAdapter(
    private var posts: ArrayList<PostEntity> = arrayListOf(),
    private val postEventListener: PostEventListener? = null
) : BaseListAdapter<PostEntity, PostViewHolder>(PostsDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post,
                parent,
                false
            )
        )

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int) =
        viewHolder.bind(
            postEntity = posts[position],
            postEventListener = postEventListener
        )

    override fun setData(data: List<PostEntity>?) {
        posts.clear()
        data?.let { posts.addAll(it) }
        submitList(posts)
    }

    override fun addData(data: List<PostEntity>?) {
        data?.let {
            posts.addAll(it)
            submitList(posts)
        }
    }
}