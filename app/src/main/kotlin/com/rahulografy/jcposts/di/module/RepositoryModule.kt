package com.rahulografy.jcposts.di.module

import com.rahulografy.jcposts.data.repo.PostsDataSource
import com.rahulografy.jcposts.data.source.local.posts.datasource.PostsLocalDataSource
import com.rahulografy.jcposts.data.source.remote.posts.datasource.PostsRemoteDataSource
import com.rahulografy.jcposts.di.qualifier.LocalData
import com.rahulografy.jcposts.di.qualifier.RemoteData
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    @LocalData
    abstract fun bindPostsLocalDataSource(
        postsLocalDataSource: PostsLocalDataSource
    ): PostsDataSource

    @Singleton
    @Binds
    @RemoteData
    abstract fun bindPostsRemoteDataSource(
        postsRemoteDataSource: PostsRemoteDataSource
    ): PostsDataSource
}