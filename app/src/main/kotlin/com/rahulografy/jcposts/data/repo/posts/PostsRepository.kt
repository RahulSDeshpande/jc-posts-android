package com.rahulografy.jcposts.data.repo.posts

import android.content.Context
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.di.ApplicationScoped
import com.rahulografy.jcposts.di.qualifier.ApplicationContext
import com.rahulografy.jcposts.di.qualifier.LocalData
import com.rahulografy.jcposts.di.qualifier.RemoteData
import com.rahulografy.jcposts.util.ext.initApp
import com.rahulografy.jcposts.util.ext.isAppInit
import com.rahulografy.jcposts.util.ext.replace
import com.rahulografy.jcposts.util.isAppOnline
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScoped
class PostsRepository @Inject constructor(
    @LocalData private val localPostsDataSource: PostsDataSource,
    @RemoteData private val remotePostsDataSource: PostsDataSource,
    @ApplicationContext private val context: Context
) : PostsDataSource {

    private var cachedPosts = arrayListOf<PostEntity>()

    private var cachedFavouritePosts = arrayListOf<PostEntity>()

    // TODO WIP
    private var isCachedPostsDirty = false

    override fun savePosts(posts: List<PostEntity>) {
        localPostsDataSource.savePosts(posts)
        cachedPosts.replace(posts)
    }

    override suspend fun updatePost(post: PostEntity) {
        refreshPosts()
        cachedPosts
            .find { it.id == post.id }
            ?.isFavourite = post.isFavourite

        localPostsDataSource.updatePost(post)
    }

    override fun getPosts(): Single<List<PostEntity>> {
        if (cachedPosts.isNotEmpty() && isCachedPostsDirty.not()) {
            return Single.just(cachedPosts)
        }

        return if (isAppOnline(context) && (cachedPosts.isEmpty() || isCachedPostsDirty)) {
            if (isAppInit()) {
                Completable
                    .fromSingle(getAndSaveRemotePosts())
                    .andThen(getAndCacheLocalPosts())
            } else {
                getAndSaveRemotePosts()
            }
        } else {
            getAndCacheLocalPosts()
        }
    }

    private fun getAndSaveRemotePosts(): Single<List<PostEntity>> {
        return remotePostsDataSource
            .getPosts()
            .doOnSuccess { posts ->
                if (isAppInit()) {
                    updateFavourites(posts)
                } else {
                    initApp()
                    localPostsDataSource.savePosts(posts)
                }
            }.doFinally {
                isCachedPostsDirty = false
            }
    }

    private fun updateFavourites(posts: List<PostEntity>) {
        val doOnSuccess =
            getAndCacheLocalPosts()
                .doOnSuccess { it ->

                    val favourites = arrayListOf<Int?>()

                    it.forEach {
                        if (it.isFavourite) {
                            favourites.add(it.id)
                        }
                    }

                    posts.forEach {
                        if (favourites.contains(it.id)) {
                            it.isFavourite = true
                        }
                    }

                    cachedPosts.replace(posts)
                    localPostsDataSource.savePosts(posts)
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