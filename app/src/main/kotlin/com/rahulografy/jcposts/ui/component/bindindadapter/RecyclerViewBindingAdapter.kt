package com.rahulografy.jcposts.ui.component.bindindadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.ui.main.postdetail.comments.adapter.CommentsAdapter
import com.rahulografy.jcposts.ui.main.posts.adapter.PostsAdapter
import com.rahulografy.jcposts.util.ext.list

@BindingAdapter("app:adapter")
fun RecyclerView?.setPostsAdapter(
    postsAdapter: PostsAdapter?
) {
    this?.apply {
        list()
        adapter = postsAdapter
    }
}

@BindingAdapter("app:items")
fun RecyclerView?.setPosts(
    items: List<PostEntity>?
) {
    with(this?.adapter as PostsAdapter?) {
        this?.setData(items)
    }
}

@BindingAdapter("app:adapter")
fun RecyclerView?.setCommentsAdapter(
    commentsAdapter: CommentsAdapter?
) {
    this?.apply {
        list()
        adapter = commentsAdapter
    }
}

@BindingAdapter("app:items")
fun RecyclerView?.setComments(
    items: List<CommentEntity>?
) {
    with(this?.adapter as CommentsAdapter?) {
        this?.setData(items)
    }
}