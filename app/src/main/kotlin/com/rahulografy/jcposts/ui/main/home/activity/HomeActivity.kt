package com.rahulografy.jcposts.ui.main.home.activity

import com.rahulografy.jcposts.BR
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.databinding.ActivityHomeBinding
import com.rahulografy.jcposts.ui.base.view.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    override val layoutRes: Int get() = R.layout.activity_home

    override val viewModelClass get() = HomeActivityViewModel::class.java

    override val bindingVariable get() = BR._all

    override fun initUi() {}
}