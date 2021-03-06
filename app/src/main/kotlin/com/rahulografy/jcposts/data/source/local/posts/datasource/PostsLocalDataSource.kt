package com.rahulografy.jcposts.data.source.local.posts.datasource

import com.rahulografy.jcposts.data.repo.posts.PostsDataSource
import com.rahulografy.jcposts.data.source.local.posts.dao.PostsDao
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.di.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class PostsLocalDataSource @Inject constructor(
    private val postsDao: PostsDao
) : PostsDataSource {

    override fun savePosts(posts: List<PostEntity>) {
        posts.map { post ->
            postsDao.savePost(post)
        }
    }

    override fun getPosts() = postsDao.getPosts()

    override suspend fun updatePost(post: PostEntity) {
        postsDao.updatePost(post)
    }

    override fun getFavouritePosts() = postsDao.getFavouritePosts()

    override fun getUnSyncedPosts() = postsDao.getUnSyncedPosts()

    override suspend fun syncPendingPosts(posts: List<PostEntity>): Boolean {
        posts.map { post ->
            post.id?.let { id ->
                postsDao.updateIsPendingSync(postId = id, isSyncPending = post.isSyncPending)
            }
        }

        return posts.isNotEmpty()
    }
}