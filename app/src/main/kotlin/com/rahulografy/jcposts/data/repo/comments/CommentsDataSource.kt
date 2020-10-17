package com.rahulografy.jcposts.data.repo.comments

import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import io.reactivex.Single

interface CommentsDataSource {

    fun saveComments(comments: List<CommentEntity>) {}

    // TODO | WIP
    // fun getComments(): Single<List<CommentEntity>> = Single.just(arrayListOf())

    // TODO | WIP
    // fun getCommentById(id: Int): Single<CommentEntity> = Single.just(null)

    fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>>

    fun refreshComments() {}
}