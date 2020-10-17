package com.rahulografy.jcposts.ui.main.home.fragment

import com.google.android.material.tabs.TabLayoutMediator
import com.rahulografy.jcposts.BR
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.databinding.FragmentHomeBinding
import com.rahulografy.jcposts.ui.base.view.BaseFragment
import com.rahulografy.jcposts.ui.main.home.fragment.adapter.HomeFragmentAdapter
import com.rahulografy.jcposts.ui.main.posts.enum.ContentType.Companion.FAVOURITES
import com.rahulografy.jcposts.ui.main.posts.enum.ContentType.Companion.POSTS
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_home

    override val toolbarId: Int get() = R.id.toolbarHome

    override var viewModelClass = HomeFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    override fun initUi() {
        initViewPager()
    }

    private fun initViewPager() {
        val contentTypes = listOf(POSTS, FAVOURITES)

        val productFragmentAdapter =
            HomeFragmentAdapter(
                contentTypes = contentTypes,
                childFragmentManager = childFragmentManager,
                lifecycle = lifecycle
            )

        viewPager2Home?.apply {
            adapter = productFragmentAdapter
            offscreenPageLimit = 2
        }

        TabLayoutMediator(
            tabLayoutHome,
            viewPager2Home
        ) { tab, position ->
            tab.text = contentTypes[position]
        }.attach()
    }
}