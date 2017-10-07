package com.unicorn.aems.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.ivotai.kotlindemo.app.ComponentsHolder


class App:Application(){

    override fun onCreate() {
        super.onCreate()
        ComponentsHolder.init(this)

        Utils.init(this)
     }

}
