package com.rahulografy.jcposts.ui.main.home.fragment.adapter

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rahulografy.jcposts.ui.main.posts.PostsFragment

class HomeFragmentAdapter(
    private val contentTypes: List<String>,
    childFragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(childFragmentManager, lifecycle) {

    override fun createFragment(position: Int) =
        PostsFragment.init(contentType = contentTypes[position])

    override fun getItemCount() = contentTypes.size
}