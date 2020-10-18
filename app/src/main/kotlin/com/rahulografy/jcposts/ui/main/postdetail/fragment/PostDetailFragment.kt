package com.rahulografy.jcposts.ui.main.postdetail.fragment

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.rahulografy.jcposts.BR
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.data.source.local.comments.model.CommentEntity
import com.rahulografy.jcposts.databinding.FragmentPostDetailBinding
import com.rahulografy.jcposts.ui.base.view.BaseDialogFragment
import com.rahulografy.jcposts.ui.main.postdetail.comments.adapter.CommentsAdapter
import com.rahulografy.jcposts.ui.main.posts.PostsSharedViewModel
import com.rahulografy.jcposts.util.ext.initViewModel
import com.rahulografy.jcposts.util.ext.show
import kotlinx.android.synthetic.main.fragment_post_detail.*
import kotlinx.android.synthetic.main.item_post.*

class PostDetailFragment :
    BaseDialogFragment<FragmentPostDetailBinding, PostDetailFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_post_detail

    override val toolbarId get() = R.id.toolbarPostDetail

    override var viewModelClass = PostDetailFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    lateinit var postsSharedViewModel: PostsSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.apply {
            postsSharedViewModel = initViewModel(PostsSharedViewModel::class.java)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) dismissFragment()
        return super.onOptionsItemSelected(item)
    }

    override fun initUi() {
        viewModel.post = postsSharedViewModel.post
        viewModel.getComments()
        initPostDetail()
    }

    override fun initObservers() {
        viewModel.apply {
            commentsMutableLiveData
                .observe(
                    lifecycleOwner = this@PostDetailFragment,
                    observer = Observer { comments ->
                        initCommentsRecyclerView(comments = comments)
                    }
                )
        }
    }

    private fun initPostDetail() {
        postsSharedViewModel.apply {

            viewDataBinding.apply {
                postEntity = post
                commentsAdapter = CommentsAdapter()
                executePendingBindings()
            }

            cbPostIsFavourite.apply {

                setOnCheckedChangeListener(null)

                isChecked = post?.isFavourite ?: false

                setOnCheckedChangeListener { _, isChecked ->

                    postsSharedViewModel.post?.apply {
                        isFavourite = isChecked
                        favouritedTime =
                            if (isChecked) System.currentTimeMillis()
                            else null
                    }

                    viewModel.updatePost(postsSharedViewModel.post)

                    postUpdated.value = post
                    favouritePostUpdated.value = post
                }
            }
        }
    }

    private fun initCommentsRecyclerView(comments: ArrayList<CommentEntity>?) {
        if (comments.isNullOrEmpty().not()) {
            showCommentsRecyclerView(show = true)

            viewModel.commentsObservableField.set(comments)
        } else {
            showCommentsRecyclerView(show = false)
        }
    }

    private fun showCommentsRecyclerView(show: Boolean) {
        rvPostDetailComments.show(show = show)
        layoutNoData.show(show = show.not())
    }

    private fun dismissFragment() {
        dismissAllowingStateLoss()
    }
}