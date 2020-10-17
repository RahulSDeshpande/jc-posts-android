package com.rahulografy.jcposts.di.module

import androidx.room.Room
import com.rahulografy.jcposts.App
import com.rahulografy.jcposts.data.source.local.comments.dao.CommentsDao
import com.rahulografy.jcposts.data.source.local.posts.dao.PostsDao
import com.rahulografy.jcposts.data.source.local.room.AppDatabase
import com.rahulografy.jcposts.util.Constants.Network.Db.DB_NAME
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(application: App) =
        Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePostsDao(database: AppDatabase): PostsDao = database.postsDao()

    @Provides
    fun provideCommentsDao(database: AppDatabase): CommentsDao = database.commentsDao()
}