package com.rahulografy.jcposts.data.repo

import com.rahulografy.jcposts.data.source.remote.posts.model.PostsResponse
import io.reactivex.Single

interface PostsDataSource {

    fun getPosts(): Single<PostsResponse>
}