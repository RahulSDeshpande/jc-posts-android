package com.rahulografy.jcposts.data.repo.posts

import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.di.ApplicationScoped
import com.rahulografy.jcposts.di.qualifier.LocalData
import com.rahulografy.jcposts.di.qualifier.RemoteData
import com.rahulografy.jcposts.util.ext.replace
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScoped
class PostsRepository @Inject constructor(
    @LocalData private val localPostsDataSource: PostsDataSource,
    @RemoteData private val remotePostsDataSource: PostsDataSource
) : PostsDataSource {

    private var cachedPosts = arrayListOf<PostEntity>()

    private var cachedFavouritePosts = arrayListOf<PostEntity>()

    // TODO WIP
    private var isCachedPostsDirty = false

    override fun savePosts(posts: List<PostEntity>) {
        localPostsDataSource.savePosts(posts)
        cachedPosts.replace(posts)
    }

    override fun updatePost(post: PostEntity) {
        cachedPosts
            .find { it.id == post.id }
            ?.isFavourite = post.isFavourite

        // TODO | WIP | CONSIDER USING COROUTINE
        Observable.just(localPostsDataSource)
            .subscribeOn(Schedulers.io())
            .subscribe { it.updatePost(post) }
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

    override fun getFavouritePosts(): Single<List<PostEntity>> {
        // TODO | VERIFY
        return localPostsDataSource
            .getFavouritePosts()
            .doOnSuccess { favouritePosts ->
                cachedFavouritePosts.replace(favouritePosts)
            }
        // TODO | WIP
        /*if (isCachedPostsDirty.not()) {
            Single.just(cachedFavouritePosts)
        } else {*/
    }

    // TODO | WIP
    override fun refreshPosts() {
        isCachedPostsDirty = true
    }
}