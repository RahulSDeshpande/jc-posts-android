package com.rahulografy.jcposts.data.source.local.comments.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import io.reactivex.Single

@Dao
interface CommentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveComment(comment: CommentEntity)

    @Query("SELECT * FROM comment ORDER BY id ASC")
    fun getComments(): Single<List<CommentEntity>>

    @Query("SELECT * FROM comment WHERE id = :id")
    fun getCommentById(id: Int): Single<CommentEntity>

    @Query("SELECT * FROM comment WHERE postId = :postId")
    fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>>
}