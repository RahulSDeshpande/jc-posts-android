package com.rahulografy.jcposts.ui.main.posts.listener

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity

interface PostEventListener {
    fun onListItemClicked(postEntity: PostEntity)
}