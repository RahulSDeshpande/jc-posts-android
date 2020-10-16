package com.rahulografy.jcposts.di.component

import com.rahulografy.jcposts.App
import com.rahulografy.jcposts.di.ApplicationScoped
import com.rahulografy.jcposts.di.module.ActivityBindingModule
import com.rahulografy.jcposts.di.module.AppModule
import com.rahulografy.jcposts.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScoped
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}