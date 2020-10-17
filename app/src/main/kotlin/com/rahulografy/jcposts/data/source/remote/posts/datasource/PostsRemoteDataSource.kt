package com.rahulografy.jcposts.data.source.remote.posts.datasource

import com.rahulografy.jcposts.data.repo.posts.PostsDataSource
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.data.source.remote.posts.service.PostsRemoteService
import com.rahulografy.jcposts.di.ApplicationScoped
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScoped
class PostsRemoteDataSource
@Inject constructor(
    private val postsRemoteService: PostsRemoteService
) : PostsDataSource {

    override fun getPosts(): Single<List<PostEntity>> = postsRemoteService.getPosts()
}