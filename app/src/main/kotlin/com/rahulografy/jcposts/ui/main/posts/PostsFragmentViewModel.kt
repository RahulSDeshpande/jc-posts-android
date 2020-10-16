package com.rahulografy.jcposts.ui.main.posts

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.rahulografy.jcposts.data.repo.PostsRepository
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.ui.base.view.BaseViewModel
import com.rahulografy.jcposts.util.SingleLiveEvent
import com.rahulografy.jcposts.util.ext.toArrayList
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsFragmentViewModel
@Inject constructor(
    private val postsRepository: PostsRepository
) : BaseViewModel() {

    val isDataProcessing = ObservableBoolean(false)

    val posts = ObservableField<PostEntity>()

    var postsObservableField = ObservableField<ArrayList<PostEntity>>()

    var postsMutableLiveData = SingleLiveEvent<ArrayList<PostEntity>>()

    val postClickEvent = SingleLiveEvent<PostEntity>()

    fun getPosts(forceApi: Boolean = false) {
        if (forceApi || postsMutableLiveData.value.isNullOrEmpty()) {
            isDataProcessing.set(true)
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
            isDataProcessing.set(false)
            postsMutableLiveData.value = postsMutableLiveData.value
        }
    }

    private fun onPostsReceived(posts: List<PostEntity>?) {
        postsMutableLiveData.value = posts.toArrayList()
        isDataProcessing.set(false)
    }

    fun openPost(postEntity: PostEntity) {
        postClickEvent.value = postEntity
    }
}