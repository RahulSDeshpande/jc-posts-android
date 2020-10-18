package com.rahulografy.jcposts.ui.main.favourites

import android.os.Bundle
import androidx.lifecycle.Observer
import com.rahulografy.jcposts.BR
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.databinding.FragmentFavouritesBinding
import com.rahulografy.jcposts.ui.base.view.BaseFragment
import com.rahulografy.jcposts.ui.main.postdetail.fragment.PostDetailFragment
import com.rahulografy.jcposts.ui.main.posts.PostsSharedViewModel
import com.rahulografy.jcposts.ui.main.posts.adapter.PostsAdapter
import com.rahulografy.jcposts.ui.main.posts.listener.PostEventListener
import com.rahulografy.jcposts.util.ext.initViewModel
import com.rahulografy.jcposts.util.ext.notifyChange
import com.rahulografy.jcposts.util.ext.show
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding, FavouritesFragmentViewModel>(),
    PostEventListener {

    override val layoutRes get() = R.layout.fragment_favourites

    override var viewModelClass = FavouritesFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    lateinit var postsSharedViewModel: PostsSharedViewModel

    private var postDetailFragment: PostDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.apply {
            postsSharedViewModel = initViewModel(PostsSharedViewModel::class.java)
        }
    }

    override fun initUi() {
        isAppOnline()

        viewModel.getPosts()

        viewDataBinding.apply {
            postsAdapter = PostsAdapter(postEventListener = this@FavouritesFragment)
            executePendingBindings()
        }
    }

    override fun initObservers() {
        postsSharedViewModel
            .favouritePostUpdated
            .observe(
                lifecycleOwner = this@FavouritesFragment,
                observer = Observer {
                    viewModel.getPosts(force = true, showLoader = false)
                }
            )

        viewModel.apply {
            postsMutableLiveData
                .observe(
                    lifecycleOwner = this@FavouritesFragment,
                    observer = Observer { posts ->
                        initPostsRecyclerView(posts = posts)
                    }
                )
        }
    }

    private fun initPostsRecyclerView(posts: ArrayList<PostEntity>?) {
        if (posts.isNullOrEmpty().not()) {
            showPostsRecyclerView(show = true)
            viewModel.postsObservableField.set(posts)
        } else {
            showPostsRecyclerView(show = false)
        }
    }

    private fun showPostsRecyclerView(show: Boolean) {
        rvPosts.show(show = show)
        layoutNoData.show(show = show.not())
    }

    override fun onListItemClicked(listPosition: Int, postEntity: PostEntity) {
        openPostDetailFragment(
            listPosition = listPosition,
            post = postEntity
        )
    }

    override fun onFavouriteIconClicked(
        listPosition: Int,
        postEntity: PostEntity
    ) {
        rvPosts.notifyChange(listPosition)
        viewModel.updatePost(postEntity)
        postsSharedViewModel.postListPosition = (postEntity.id ?: 0) - 1
        postsSharedViewModel.postUpdated.value = postEntity
    }

    private fun openPostDetailFragment(listPosition: Int, post: PostEntity) {
        if (postDetailFragment == null) {
            postDetailFragment = PostDetailFragment()
        }

        if (postDetailFragment?.isVisible != true) {
            postsSharedViewModel.postListPosition = (post.id ?: 0) - 1
            postsSharedViewModel.post = post
            postDetailFragment?.show(childFragmentManager, postDetailFragment?.tag)
        }
    }
}