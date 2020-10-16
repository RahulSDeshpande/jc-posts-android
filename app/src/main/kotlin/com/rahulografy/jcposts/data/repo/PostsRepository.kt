package com.rahulografy.jcposts.data.repo

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.di.ApplicationScoped
import com.rahulografy.jcposts.di.qualifier.LocalData
import com.rahulografy.jcposts.di.qualifier.RemoteData
import com.rahulografy.jcposts.util.ext.replace
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScoped
class PostsRepository @Inject constructor(
    @LocalData private val localPostsDataSource: PostsDataSource,
    @RemoteData private val remotePostsDataSource: PostsDataSource
) : PostsDataSource {

    private var cachedPosts = arrayListOf<PostEntity>()

    private var cachedFavoritePosts = arrayListOf<PostEntity>()

    // TODO WIP
    private var isCachedPostsDirty = false

    override fun savePosts(posts: List<PostEntity>) {
        localPostsDataSource.savePosts(posts)
        cachedPosts.replace(posts)
    }

    override fun updatePost(post: PostEntity) {
        cachedPosts
            .find { it.id == post.id }
            ?.isFavorite = post.isFavorite

        return localPostsDataSource.updatePost(post)
    }

    override fun getPosts(): Single<List<PostEntity>> {
        if (cachedPosts.isNotEmpty() && isCachedPostsDirty.not()) {
            return Single.just(cachedPosts)
        }

        return if (isCachedPostsDirty) {
            getAndSaveRemotePosts()
        } else {
            getAndCacheLocalPosts()
        }
    }

    private fun getAndSaveRemotePosts(): Single<List<PostEntity>> {
        return remotePostsDataSource
            .getPosts()
            .doOnSuccess { posts ->
                cachedPosts.replace(posts)
                localPostsDataSource.savePosts(posts)
            }.doFinally {
                isCachedPostsDirty = false
            }
    }

    private fun getAndCacheLocalPosts(): Single<List<PostEntity>> {
        return localPostsDataSource
            .getPosts()
            .doOnSuccess { posts ->
                cachedPosts.replace(posts)
            }
    }

    override fun getFavoritePosts(): Single<List<PostEntity>> {
        // TODO | VERIFY
        return if (isCachedPostsDirty.not()) {
            Single.just(cachedFavoritePosts)
        } else {
            localPostsDataSource
                .getFavoritePosts()
                .doOnSuccess { favoritePosts ->
                    cachedFavoritePosts.replace(favoritePosts)
                }
        }
    }

    // TODO | WIP
    override fun refreshPosts() {
        isCachedPostsDirty = true
    }
}