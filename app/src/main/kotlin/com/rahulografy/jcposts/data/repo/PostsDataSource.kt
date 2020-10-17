package com.rahulografy.jcposts.data.repo

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import io.reactivex.Single

interface PostsDataSource {

    fun savePosts(posts: List<PostEntity>) {}

    fun updatePost(post: PostEntity) {}

    fun getPosts(): Single<List<PostEntity>>

    fun getFavouritePosts(): Single<List<PostEntity>> = Single.just(arrayListOf())

    fun refreshPosts() {}
}