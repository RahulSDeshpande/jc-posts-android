package com.rahulografy.jcposts.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rahulografy.jcposts.data.source.local.comments.dao.CommentsDao
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.data.source.local.posts.dao.PostsDao
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.util.Constants.Network.Db.DB_NAME

@Database(
    entities = [
        PostEntity::class,
        CommentEntity::class
    ],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    @Volatile
    private var appDatabase: AppDatabase? = null

    @Synchronized
    open fun getInstance(context: Context): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = init(context = context)
        }
        return appDatabase
    }

    private fun init(context: Context): AppDatabase? {
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun postsDao(): PostsDao

    abstract fun commentsDao(): CommentsDao

    // TODO | HANDLE CUSTOM DB MIGRATIONS
}