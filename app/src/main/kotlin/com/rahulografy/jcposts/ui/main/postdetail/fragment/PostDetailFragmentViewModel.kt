package com.rahulografy.jcposts.ui.main.postdetail.fragment

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.rahulografy.jcposts.data.repo.comments.CommentsRepository
import com.rahulografy.jcposts.data.repo.posts.PostsRepository
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.ui.base.view.BaseViewModel
import com.rahulografy.jcposts.util.SingleLiveEvent
import com.rahulografy.jcposts.util.ext.toArrayList
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostDetailFragmentViewModel
@Inject constructor(
    private val postsRepository: PostsRepository,
    private val commentsRepository: CommentsRepository
) : BaseViewModel() {

    val isDataProcessing = ObservableBoolean(false)

    var post: PostEntity? = null

    val comments = ObservableField<CommentEntity>()

    var commentsObservableField = ObservableField<ArrayList<CommentEntity>>()

    var commentsMutableLiveData = SingleLiveEvent<ArrayList<CommentEntity>>()

    fun getComments(forceApi: Boolean = false) {
        if ((post != null && post?.id != null)
            && (forceApi || commentsMutableLiveData.value.isNullOrEmpty())
        ) {
            isDataProcessing.set(true)

            addDisposable(
                disposable = commentsRepository
                    .getCommentsByPostId(postId = post!!.id!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(scheduleInMainThread())
                    .subscribe({
                        onCommentsReceived(it)
                    }, { error ->
                        commentsMutableLiveData.value = null
                        error.printStackTrace()
                    })
            )
        } else {
            isDataProcessing.set(false)
            commentsMutableLiveData.value = commentsMutableLiveData.value
        }
    }

    private fun onCommentsReceived(comments: List<CommentEntity>?) {
        commentsMutableLiveData.value = comments.toArrayList()
        isDataProcessing.set(false)
    }

    fun updatePost(post: PostEntity?) {
        post?.let {
            viewModelScope.launch(Dispatchers.IO) {
                postsRepository.updatePost(it)
            }
        }
    }
}