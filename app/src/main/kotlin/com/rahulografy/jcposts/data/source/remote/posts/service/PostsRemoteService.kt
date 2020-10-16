package com.rahulografy.jcposts.data.source.remote.posts.service

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.util.Constants.Network.Api.URL_GET_POSTS
import io.reactivex.Single
import retrofit2.http.GET

interface PostsRemoteService {

    @GET(URL_GET_POSTS)
    fun getPosts(): Single<List<PostEntity>>
}