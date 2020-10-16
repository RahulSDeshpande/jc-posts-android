package com.rahulografy.jcposts.ui.main.home.fragment

import com.rahulografy.jcposts.BR
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.databinding.FragmentHomeBinding
import com.rahulografy.jcposts.ui.base.view.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_home

    override val toolbarId: Int get() = R.id.toolbarHome

    override var viewModelClass = HomeFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    override fun initUi() {}

    override fun initObservers() {}
}