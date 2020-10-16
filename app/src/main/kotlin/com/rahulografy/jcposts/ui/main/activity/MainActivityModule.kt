package com.rahulografy.jcposts.ui.main.activity

import com.rahulografy.jcposts.di.FragmentScoped
import com.rahulografy.jcposts.ui.main.home.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}