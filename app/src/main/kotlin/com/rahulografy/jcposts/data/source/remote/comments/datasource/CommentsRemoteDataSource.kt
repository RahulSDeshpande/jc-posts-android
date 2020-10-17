package com.rahulografy.jcposts.data.source.remote.comments.datasource

import com.rahulografy.jcposts.data.repo.comments.CommentsDataSource
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.data.source.remote.comments.service.CommentsRemoteService
import com.rahulografy.jcposts.di.ApplicationScoped
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScoped
class CommentsRemoteDataSource
@Inject constructor(
    private val commentsRemoteService: CommentsRemoteService
) : CommentsDataSource {

    override fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>> =
        commentsRemoteService.getComments(postId = postId)
}