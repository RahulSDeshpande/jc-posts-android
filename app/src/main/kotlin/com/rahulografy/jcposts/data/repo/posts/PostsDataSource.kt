package com.rahulografy.jcposts.data.repo.posts

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import io.reactivex.Single

interface PostsDataSource {

    fun savePosts(posts: List<PostEntity>) {}

    suspend fun updatePost(post: PostEntity) {}

    fun getPosts(): Single<List<PostEntity>>

    fun getFavouritePosts(): Single<List<PostEntity>> = Single.just(arrayListOf())

    fun refreshPosts() {}

    fun getUnSyncedPosts(): Single<List<PostEntity>> = Single.just(arrayListOf())

    suspend fun syncPendingPosts(posts: List<PostEntity>) = false
}