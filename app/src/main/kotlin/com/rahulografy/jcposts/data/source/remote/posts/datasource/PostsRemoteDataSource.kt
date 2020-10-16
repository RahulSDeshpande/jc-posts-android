package com.rahulografy.jcposts.data.source.remote.posts.datasource

import com.rahulografy.jcposts.data.repo.PostsDataSource
import com.rahulografy.jcposts.data.source.remote.posts.model.PostsResponse
import com.rahulografy.jcposts.data.source.remote.posts.service.PostsRemoteService
import com.rahulografy.jcposts.di.ApplicationScoped
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScoped
class PostsRemoteDataSource
@Inject constructor(
    private val postsRemoteService: PostsRemoteService
) : PostsDataSource {

    override fun getPosts(): Single<PostsResponse> = postsRemoteService.getPosts()
}