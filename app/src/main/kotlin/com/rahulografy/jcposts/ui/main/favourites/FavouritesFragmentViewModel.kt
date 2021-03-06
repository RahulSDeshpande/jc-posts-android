package com.rahulografy.jcposts.ui.main.favourites

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.rahulografy.jcposts.data.repo.posts.PostsRepository
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.ui.base.view.BaseViewModel
import com.rahulografy.jcposts.util.SingleLiveEvent
import com.rahulografy.jcposts.util.ext.toArrayList
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesFragmentViewModel
@Inject constructor(
    private val postsRepository: PostsRepository
) : BaseViewModel() {

    val isDataProcessing = ObservableBoolean(false)

    var postsObservableField = ObservableField<ArrayList<PostEntity>>()

    var postsMutableLiveData = SingleLiveEvent<ArrayList<PostEntity>>()

    fun getPosts(force: Boolean = false, showLoader: Boolean = true) {

        if (force || postsMutableLiveData.value.isNullOrEmpty()) {

            if (showLoader) {
                isDataProcessing.set(true)
            }

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
        } else {
            if (showLoader) {
                isDataProcessing.set(false)
            }
            postsMutableLiveData.value = postsMutableLiveData.value
        }
    }

    private fun onPostsReceived(posts: List<PostEntity>?) {
        postsMutableLiveData.value = posts.toArrayList()
        isDataProcessing.set(false)
    }

    fun updatePost(postEntity: PostEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            postsRepository.updatePost(postEntity)
            getPosts(force = true, showLoader = false)
        }
    }
}