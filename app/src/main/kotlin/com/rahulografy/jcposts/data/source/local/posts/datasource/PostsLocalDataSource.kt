package com.rahulografy.jcposts.data.source.local.posts.datasource

import com.rahulografy.jcposts.data.repo.PostsDataSource
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

    override fun getPosts() = postsDao.getAllPosts()
}