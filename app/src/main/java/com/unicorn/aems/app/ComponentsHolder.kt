package com.ivotai.kotlindemo.app

import com.ivotai.kotlindemo.app.inject.component.DaggerAppComponent
import com.ivotai.kotlindemo.app.inject.module.BoxModule
import com.ivotai.kotlindemo.app.inject.module.NetworkModule
import com.unicorn.aems.app.App


object ComponentsHolder {

    private lateinit var app: App

    fun init(app: App) {
        ComponentsHolder.app = app
    }

    private val appComponent by lazy {
        DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .boxModule(BoxModule(app))
                .build()
    }

    val airportComponent by lazy {
        appComponent.getAirportComponent()
    }

}