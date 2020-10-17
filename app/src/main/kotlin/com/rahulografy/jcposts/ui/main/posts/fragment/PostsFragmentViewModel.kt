package com.rahulografy.jcposts.ui.main.posts.fragment

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.rahulografy.jcposts.data.repo.posts.PostsRepository
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.ui.base.view.BaseViewModel
import com.rahulografy.jcposts.ui.main.posts.enum.ContentType.Companion.POSTS
import com.rahulografy.jcposts.util.SingleLiveEvent
import com.rahulografy.jcposts.util.ext.toArrayList
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsFragmentViewModel
@Inject constructor(
    private val postsRepository: PostsRepository
) : BaseViewModel() {

    var contentType = POSTS

    val isDataProcessing = ObservableBoolean(false)

    val posts = ObservableField<PostEntity>()

    var postsObservableField = ObservableField<ArrayList<PostEntity>>()

    var postsMutableLiveData = SingleLiveEvent<ArrayList<PostEntity>>()

    fun getPosts(forceApi: Boolean = false) {
        if (forceApi || postsMutableLiveData.value.isNullOrEmpty()) {
            isDataProcessing.set(true)

            if (contentType == POSTS) {
                addDisposable(
                    disposable = postsRepository
                        .getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(scheduleInMainThread())
                        .subscribe({
                            onPostsReceived(it)
                        }, { error ->
                            postsMutableLiveData.value = null
                            error.printStackTrace()
                        })
                )
            } else {
                addDisposable(
                    disposable = postsRepository
                        .getFavouritePosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(scheduleInMainThread())
                        .subscribe({
                            onPostsReceived(it)
                        }, { error ->
                            postsMutableLiveData.value = null
                            error.printStackTrace()
                        })
                )
            }
        } else {
            isDataProcessing.set(false)
            postsMutableLiveData.value = postsMutableLiveData.value
        }
    }

    private fun onPostsReceived(posts: List<PostEntity>?) {
        postsMutableLiveData.value = posts.toArrayList()
        isDataProcessing.set(false)
    }

    fun updatePost(postEntity: PostEntity) {

        postsRepository.updatePost(postEntity)
    }
}