package com.rahulografy.jcposts.ui.main.activity

import com.rahulografy.jcposts.di.FragmentScoped
import com.rahulografy.jcposts.ui.main.home.fragment.HomeFragment
import com.rahulografy.jcposts.ui.main.posts.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindPostsFragment(): PostsFragment
}