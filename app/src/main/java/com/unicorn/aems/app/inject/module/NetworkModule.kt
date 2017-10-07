package com.ivotai.kotlindemo.app.inject.module

import com.google.gson.Gson
import com.ivotai.kotlindemo.app.inject.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun okHttpClient(): OkHttpClient =  OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(makeHeadersInterceptor())
            .build()

    private val apkBaseUrl = "http://ivotai.natapp1.cc/aems/"

    @AppScope
    @Provides
    @Named(value = "apk")
    fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(apkBaseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

}