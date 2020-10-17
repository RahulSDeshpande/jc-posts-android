package com.rahulografy.jcposts.ui.main.postdetail.comments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.ui.base.adapter.BaseListAdapter

class CommentsAdapter(
    private var comments: ArrayList<CommentEntity> = arrayListOf()
) : BaseListAdapter<CommentEntity, CommentViewHolder>(CommentsDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CommentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_comment,
                parent,
                false
            )
        )

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(viewHolder: CommentViewHolder, position: Int) =
        viewHolder.bind(commentEntity = comments[position])

    override fun setData(data: List<CommentEntity>?) {
        comments.clear()
        data?.let { comments.addAll(it) }
        submitList(comments)
    }

    override fun addData(data: List<CommentEntity>?) {
        data?.let {
            comments.addAll(it)
            submitList(comments)
        }
    }
}