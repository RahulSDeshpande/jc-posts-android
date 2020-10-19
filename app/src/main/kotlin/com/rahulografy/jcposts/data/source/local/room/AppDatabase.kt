package com.rahulografy.jcposts.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahulografy.jcposts.data.source.local.comments.dao.CommentsDao
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.data.source.local.posts.dao.PostsDao
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity

@Database(
    entities = [
        PostEntity::class,
        CommentEntity::class
    ],
    version = 5,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    abstract fun commentsDao(): CommentsDao

    // TODO | HANDLE CUSTOM DB MIGRATIONS
}