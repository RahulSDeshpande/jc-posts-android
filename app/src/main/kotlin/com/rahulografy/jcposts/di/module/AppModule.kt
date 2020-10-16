package com.rahulografy.jcposts.di.module

import android.app.Application
import android.content.Context
import com.rahulografy.jcposts.App
import com.rahulografy.jcposts.di.ApplicationScoped
import com.rahulografy.jcposts.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    @ApplicationScoped
    @ApplicationContext
    abstract fun bindApplicationContext(app: App): Context

    @Binds
    @ApplicationScoped
    abstract fun bindApplication(app: App): Application
}