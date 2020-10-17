package com.rahulografy.jcposts.data.source.local.posts.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import io.reactivex.Single

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePost(post: PostEntity)

    @Query("SELECT * FROM post ORDER BY id ASC")
    fun getAllPosts(): Single<List<PostEntity>>

    @Query("SELECT * FROM post WHERE id = :postId")
    fun getPostById(postId: Int): Single<PostEntity>

    // TODO | ACCEPT 'postId' INSTEAD OF 'PostEntity'
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updatePost(post: PostEntity)

    @Query("SELECT * FROM post WHERE isFavourite = 1 ORDER BY favouritedTime DESC")
    fun getFavouritePosts(): Single<List<PostEntity>>
}