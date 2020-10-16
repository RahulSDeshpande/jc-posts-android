package com.rahulografy.jcposts.di.module

import com.rahulografy.jcposts.di.ActivityScoped
import com.rahulografy.jcposts.ui.main.activity.MainActivity
import com.rahulografy.jcposts.ui.main.activity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindHomeActivity(): MainActivity
}