package com.rahulografy.jcposts.ui.main.posts

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.ui.base.view.BaseViewModel
import com.rahulografy.jcposts.util.SingleLiveEvent

class PostsSharedViewModel : BaseViewModel() {

    var postListPosition = -1

    var post: PostEntity? = null

    var postUpdated = SingleLiveEvent<PostEntity>()
}