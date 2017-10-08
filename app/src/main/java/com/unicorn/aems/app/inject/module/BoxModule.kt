package com.unicorn.aems.app.inject.module

import com.ivotai.kotlindemo.app.inject.scope.AppScope
import com.unicorn.aems.BuildConfig
import com.unicorn.aems.airport.model.entity.MyObjectBox
import com.unicorn.aems.app.App
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser


@Module
class BoxModule(val app: App) {

    @AppScope
    @Provides
    fun boxStore(): BoxStore {
        val boxStore = MyObjectBox.builder().androidContext(app).build()
        if (BuildConfig.DEBUG) {
            AndroidObjectBrowser(boxStore).start(app)
        }
        return boxStore
    }

}