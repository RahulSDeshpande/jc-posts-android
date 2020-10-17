package com.rahulografy.jcposts.di.module

import com.rahulografy.jcposts.data.repo.comments.CommentsDataSource
import com.rahulografy.jcposts.data.repo.posts.PostsDataSource
import com.rahulografy.jcposts.data.source.local.comments.datasource.CommentsLocalDataSource
import com.rahulografy.jcposts.data.source.local.posts.datasource.PostsLocalDataSource
import com.rahulografy.jcposts.data.source.remote.comments.datasource.CommentsRemoteDataSource
import com.rahulografy.jcposts.data.source.remote.posts.datasource.PostsRemoteDataSource
import com.rahulografy.jcposts.di.qualifier.LocalData
import com.rahulografy.jcposts.di.qualifier.RemoteData
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    @LocalData
    abstract fun bindPostsLocalDataSource(
        postsLocalDataSource: PostsLocalDataSource
    ): PostsDataSource

    @Binds
    @RemoteData
    abstract fun bindPostsRemoteDataSource(
        postsRemoteDataSource: PostsRemoteDataSource
    ): PostsDataSource

    @Binds
    @LocalData
    abstract fun bindCommentsLocalDataSource(
        commentsLocalDataSource: CommentsLocalDataSource
    ): CommentsDataSource

    @Binds
    @RemoteData
    abstract fun bindCommentsRemoteDataSource(
        commentsRemoteDataSource: CommentsRemoteDataSource
    ): CommentsDataSource
}