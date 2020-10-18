package com.rahulografy.jcposts.ui.main.activity

import com.rahulografy.jcposts.di.FragmentScoped
import com.rahulografy.jcposts.ui.main.favourites.FavouritesFragment
import com.rahulografy.jcposts.ui.main.home.fragment.HomeFragment
import com.rahulografy.jcposts.ui.main.postdetail.fragment.PostDetailFragment
import com.rahulografy.jcposts.ui.main.posts.fragment.PostsFragment
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

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindFavouritesFragment(): FavouritesFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindPostDetailFragment(): PostDetailFragment
}