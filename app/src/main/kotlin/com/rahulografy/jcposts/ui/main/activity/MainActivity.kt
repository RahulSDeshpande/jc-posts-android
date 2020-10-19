package com.rahulografy.jcposts.ui.main.activity

import com.google.android.material.snackbar.Snackbar
import com.rahulografy.jcposts.BR
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.databinding.ActivityHomeBinding
import com.rahulografy.jcposts.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class MainActivity : BaseActivity<ActivityHomeBinding, MainActivityViewModel>() {

    override val layoutRes: Int get() = R.layout.activity_home

    override val viewModelClass get() = MainActivityViewModel::class.java

    override val bindingVariable get() = BR._all

    override fun initUi() {}

    fun showSnackbar(message: String) {
        Snackbar
            .make(
                coordinatorLayoutMain,
                message,
                Snackbar.LENGTH_LONG
            ).show()
    }
}