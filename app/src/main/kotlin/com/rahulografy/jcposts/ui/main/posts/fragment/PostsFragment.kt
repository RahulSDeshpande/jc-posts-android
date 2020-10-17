package com.rahulografy.jcposts.ui.main.posts.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.rahulografy.jcposts.BR
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.databinding.FragmentPostsBinding
import com.rahulografy.jcposts.ui.base.view.BaseFragment
import com.rahulografy.jcposts.ui.main.postdetail.fragment.PostDetailFragment
import com.rahulografy.jcposts.ui.main.posts.PostsSharedViewModel
import com.rahulografy.jcposts.ui.main.posts.adapter.PostsAdapter
import com.rahulografy.jcposts.ui.main.posts.enum.ContentType.Companion.POSTS
import com.rahulografy.jcposts.ui.main.posts.listener.PostEventListener
import com.rahulografy.jcposts.util.ext.initViewModel
import com.rahulografy.jcposts.util.ext.notifyChange
import com.rahulografy.jcposts.util.ext.show
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment :
    BaseFragment<FragmentPostsBinding, PostsFragmentViewModel>(),
    PostEventListener {

    companion object {
        private const val ARG_CONTENT_TYPE = "ARG_CONTENT_TYPE"

        fun init(contentType: String): PostsFragment {
            val postFragment = PostsFragment()
            val bundle = Bundle()
            bundle.putString(ARG_CONTENT_TYPE, contentType)
            postFragment.arguments = bundle
            return postFragment
        }
    }

    override val layoutRes get() = R.layout.fragment_posts

    override var viewModelClass = PostsFragmentViewModel::class.java

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
        viewModel.contentType = arguments?.getString(ARG_CONTENT_TYPE) ?: POSTS

        isAppOnline()

        viewModel.getPosts()

        viewDataBinding.apply {
            postsAdapter = PostsAdapter(postEventListener = this@PostsFragment)
            executePendingBindings()
        }
    }

    override fun initObservers() {
        postsSharedViewModel
            .postUpdated
            .observe(
                lifecycleOwner = this@PostsFragment,
                observer = Observer { }
            )

        viewModel.apply {
            postsMutableLiveData
                .observe(
                    lifecycleOwner = this@PostsFragment,
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
            postId = postEntity
        )
    }

    override fun onFavouriteIconClicked(
        listPosition: Int,
        postEntity: PostEntity
    ) {
        rvPosts.notifyChange(listPosition)
        viewModel.updatePost(postEntity)
    }

    private fun openPostDetailFragment(listPosition: Int, postId: PostEntity) {
        if (postDetailFragment == null) {
            postDetailFragment = PostDetailFragment()
        }

        if (postDetailFragment?.isVisible != true) {
            postsSharedViewModel.postListPosition = listPosition
            postsSharedViewModel.post = postId
            postDetailFragment?.show(childFragmentManager, postDetailFragment?.tag)
        }
    }
}