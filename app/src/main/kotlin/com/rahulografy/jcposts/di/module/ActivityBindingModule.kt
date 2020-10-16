package com.rahulografy.jcposts.di.module

import com.rahulografy.jcposts.di.ActivityScoped
import com.rahulografy.jcposts.ui.main.home.activity.HomeActivity
import com.rahulografy.jcposts.ui.main.home.activity.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity
}