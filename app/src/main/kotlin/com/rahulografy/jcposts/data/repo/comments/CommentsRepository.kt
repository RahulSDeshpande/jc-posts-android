package com.rahulografy.jcposts.data.repo.comments

import android.content.Context
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.di.ApplicationScoped
import com.rahulografy.jcposts.di.qualifier.ApplicationContext
import com.rahulografy.jcposts.di.qualifier.LocalData
import com.rahulografy.jcposts.di.qualifier.RemoteData
import com.rahulografy.jcposts.util.isAppOnline
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScoped
class CommentsRepository @Inject constructor(
    @LocalData private val localCommentsDataSource: CommentsDataSource,
    @RemoteData private val remoteCommentsDataSource: CommentsDataSource,
    @ApplicationContext private val context: Context
) : CommentsDataSource {

    private var cachedPostIdWiseCommentsMap = linkedMapOf<Int, List<CommentEntity>>()

    // TODO WIP
    private var isCachedCommentsDirty = false

    override fun saveComments(comments: List<CommentEntity>) {
        localCommentsDataSource.saveComments(comments)
        comments[0].postId?.let { postId ->
            cachedPostIdWiseCommentsMap[postId] = comments
        }
    }

    // TODO | REMOVE !!
    override fun getCommentsByPostId(postId: Int): Single<List<CommentEntity>> {
        if (isCachedCommentsDirty.not()
            && cachedPostIdWiseCommentsMap.containsKey(postId)
            && cachedPostIdWiseCommentsMap[postId]!!.isNotEmpty()
        ) {
            return Single.just(cachedPostIdWiseCommentsMap[postId])
        }

        return if (isAppOnline(context)
            && (cachedPostIdWiseCommentsMap.containsKey(postId).not()
                    || cachedPostIdWiseCommentsMap[postId]!!.isEmpty()
                    || isCachedCommentsDirty)
        ) {
            getAndSaveRemoteComments(postId = postId)
        } else {
            getAndCacheLocalComments(postId = postId)
        }
    }

    private fun getAndSaveRemoteComments(postId: Int): Single<List<CommentEntity>> {
        return remoteCommentsDataSource
            .getCommentsByPostId(postId = postId)
            .doOnSuccess { comments ->
                localCommentsDataSource.saveComments(comments)
                cachedPostIdWiseCommentsMap[postId] = comments
            }.doFinally {
                isCachedCommentsDirty = false
            }
    }

    private fun getAndCacheLocalComments(postId: Int): Single<List<CommentEntity>> {
        return localCommentsDataSource
            .getCommentsByPostId(postId = postId)
            .doOnSuccess { comments ->
                cachedPostIdWiseCommentsMap[postId] = comments
            }
    }

    override fun refreshComments() {
        isCachedCommentsDirty = true
    }
}