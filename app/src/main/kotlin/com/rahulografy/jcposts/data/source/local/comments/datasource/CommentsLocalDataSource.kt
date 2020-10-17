package com.rahulografy.jcposts.data.source.local.comments.datasource

import com.rahulografy.jcposts.data.repo.comments.CommentsDataSource
import com.rahulografy.jcposts.data.source.local.comments.dao.CommentsDao
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.di.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CommentsLocalDataSource @Inject constructor(
    private val commentsDao: CommentsDao
) : CommentsDataSource {

    override fun saveComments(comments: List<CommentEntity>) {
        comments.map { comment ->
            commentsDao.saveComment(comment)
        }
    }

    // TODO | WIP
    // override fun getCommentById(id: Int) = commentsDao.getCommentById(id = id)

    override fun getCommentsByPostId(postId: Int) = commentsDao.getCommentsByPostId(postId = postId)
}