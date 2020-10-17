package com.rahulografy.jcposts.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahulografy.jcposts.data.source.local.posts.dao.PostsDao
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity

@Database(
    entities = [
        PostEntity::class
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    // TODO | HANDLE DB MIGRATIONS
}