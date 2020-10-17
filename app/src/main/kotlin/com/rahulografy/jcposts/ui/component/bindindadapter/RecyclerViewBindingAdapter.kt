package com.rahulografy.jcposts.ui.component.bindindadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.ui.main.posts.adapter.PostsAdapter
import com.rahulografy.jcposts.util.ext.list

@BindingAdapter("app:adapter")
fun RecyclerView?.setAdapter(
    postsAdapter: PostsAdapter?
) {
    this?.apply {
        list()
        adapter = postsAdapter
    }
}

@BindingAdapter("app:items")
fun RecyclerView?.setProducts(
    items: List<PostEntity>?
) {
    with(this?.adapter as PostsAdapter?) {
        this?.setData(items)
    }
}