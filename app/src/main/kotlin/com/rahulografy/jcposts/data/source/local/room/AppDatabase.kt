package com.rahulografy.jcposts.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rahulografy.jcposts.data.source.local.comments.dao.CommentsDao
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.data.source.local.posts.dao.PostsDao
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.util.Constants.Network.Db.ENTITY_POST
import com.rahulografy.jcposts.util.Constants.Network.Db.ENTITY_POST_NEW

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

    // WIP
    companion object {
        // TODO | VERIFY CUSTOM DB MIGRATIONS
        val migrateFrom3To5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {

                // Create the new table
                database.execSQL(
                    "CREATE TABLE $ENTITY_POST_NEW (id INTEGER, body TEXT, title TEXT, userId INTEGER, isFavourite BOOLEAN DEFUALT false, favouritedTime LONG, isSyncPending BOOLEAN DEFUALT false, PRIMARY KEY(id))"
                )

                // Copy the data from 'post' to 'post-new'
                database.execSQL(
                    "INSERT INTO $ENTITY_POST_NEW (id, body, title, userId, isFavourite, favouritedTime) SELECT id, body, title, userId, isFavourite, favouritedTime FROM $ENTITY_POST"
                )

                // Drop the old table 'post'
                database.execSQL("DROP TABLE $ENTITY_POST")

                // Change the table name from 'post-new' to 'post'
                database.execSQL("ALTER TABLE $ENTITY_POST_NEW RENAME TO $ENTITY_POST")
            }
        }
    }
}