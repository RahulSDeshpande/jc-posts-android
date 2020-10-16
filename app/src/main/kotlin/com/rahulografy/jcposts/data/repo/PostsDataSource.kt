package com.rahulografy.jcposts.data.repo

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import io.reactivex.Single

interface PostsDataSource {

    fun savePosts(posts: List<PostEntity>) {}

    fun getPosts(): Single<List<PostEntity>>

    // TODO | ACCEPT 'postId' INSTEAD OF 'PostEntity'
    fun favoritePost(post: PostEntity) {}

    fun getFavoritePosts(): Single<List<PostEntity>>? = null

    fun refreshPosts() {}
}