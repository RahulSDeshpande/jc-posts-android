package com.rahulografy.jcposts.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulografy.jcposts.di.ApplicationScoped
import com.rahulografy.jcposts.di.ViewModelKey
import com.rahulografy.jcposts.ui.main.activity.MainActivityViewModel
import com.rahulografy.jcposts.ui.main.home.fragment.HomeFragmentViewModel
import com.rahulografy.jcposts.ui.main.postdetail.fragment.PostDetailFragmentViewModel
import com.rahulografy.jcposts.ui.main.posts.fragment.PostsFragmentViewModel
import com.rahulografy.jcposts.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        AppModule::class,
        RepositoryModule::class
    ]
)
abstract class ViewModelModule {

    @ApplicationScoped
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindHomeActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel: HomeFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsFragmentViewModel::class)
    abstract fun bindPostsFragmentViewModel(viewModel: PostsFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailFragmentViewModel::class)
    abstract fun bindPostDetailFragmentViewModel(viewModel: PostDetailFragmentViewModel): ViewModel
}