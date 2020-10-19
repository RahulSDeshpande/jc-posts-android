package com.rahulografy.jcposts.ui.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rahulografy.jcposts.R
import com.rahulografy.jcposts.util.event.InternetConnectionEvent
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.imaginativeworld.oopsnointernet.ConnectionCallback
import org.imaginativeworld.oopsnointernet.NoInternetSnackbar
import javax.inject.Inject

abstract class BaseActivity<VDB : ViewDataBinding, BVM : BaseViewModel> :
    DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: VDB

    protected abstract val viewModelClass: Class<BVM>

    private lateinit var viewModel: BVM

    abstract val bindingVariable: Int

    private var noInternetSnackbar: NoInternetSnackbar? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[viewModelClass]

        viewDataBinding = DataBindingUtil.setContentView(this, layoutRes)

        viewDataBinding.apply {
            setVariable(
                bindingVariable,
                viewModel
            )
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }

        viewModel.start()

        initUi()

        initNetworkMonitorSnackbar()
    }

    abstract fun initUi()

    override fun onPause() {
        super.onPause()
        noInternetSnackbar?.destroy()
    }

    override fun onDestroy() {
        viewModel.stop()
        super.onDestroy()
    }

    private fun initNetworkMonitorSnackbar() {
        noInternetSnackbar =
            NoInternetSnackbar
                .Builder(
                    activity = this,
                    parent = findViewById(android.R.id.content)
                )
                .apply {
                    indefinite = true
                    noInternetConnectionMessage = getString(R.string.msg_no_internet)
                    onAirplaneModeMessage = getString(R.string.msg_airplane_mode_is_turned_on)
                    snackbarActionText = getString(R.string.settings)
                    showActionToDismiss = true
                    snackbarDismissActionText = getString(R.string.ok)
                    connectionCallback = object : ConnectionCallback {
                        override fun hasActiveConnection(hasActiveConnection: Boolean) {
                            // isAppOnline = hasActiveConnection
                            onInternetConnectionUpdate(isActive = hasActiveConnection)
                        }
                    }
                }
                .build()
    }

    open fun onInternetConnectionUpdate(isActive: Boolean) {
        EventBus.getDefault().post(InternetConnectionEvent(isActive = isActive))
    }
}