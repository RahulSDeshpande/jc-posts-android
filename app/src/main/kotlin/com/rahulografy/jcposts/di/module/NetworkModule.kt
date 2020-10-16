package com.rahulografy.jcposts.di.module

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rahulografy.jcposts.data.source.remote.posts.service.PostsRemoteService
import com.rahulografy.jcposts.di.ApplicationScoped
import com.rahulografy.jcposts.util.Constants.Network.Api.URL_BASE
import com.rahulografy.jcposts.util.Constants.Network.Cache.NAME
import com.rahulografy.jcposts.util.Constants.Network.Timeout.CONNECTION
import com.rahulografy.jcposts.util.Constants.Network.Timeout.READ
import com.rahulografy.jcposts.util.Constants.Network.Timeout.WRITE
import com.rahulografy.jcposts.util.Memory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    private fun buildOkHttpClient(application: Application): OkHttpClient =
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(BODY))
            .connectTimeout(CONNECTION, TimeUnit.SECONDS)
            .writeTimeout(WRITE, TimeUnit.SECONDS)
            .readTimeout(READ, TimeUnit.SECONDS)
            .cache(
                Cache(
                    File(application.cacheDir, NAME),
                    Memory.calculateCacheSize(context = application, size = .25f)
                )
            )
            .build()

    @Provides
    @ApplicationScoped
    fun provideOkHttpClient(application: Application): OkHttpClient = buildOkHttpClient(application)

    @Provides
    @ApplicationScoped
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .client(okHttpClient)
            .build()

    @Provides
    @ApplicationScoped
    fun providePostsRemoteService(retrofit: Retrofit): PostsRemoteService =
        retrofit.create(PostsRemoteService::class.java)
}