package com.rahulografy.jcposts.di.module

import androidx.room.Room
import com.rahulografy.jcposts.App
import com.rahulografy.jcposts.data.source.local.comments.dao.CommentsDao
import com.rahulografy.jcposts.data.source.local.posts.dao.PostsDao
import com.rahulografy.jcposts.data.source.local.room.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(application: App): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "bl.db").build()
    }

    @Provides
    fun providePostsDao(database: AppDatabase): PostsDao = database.postsDao()

    @Provides
    fun provideCommentsDao(database: AppDatabase): CommentsDao = database.commentsDao()
}