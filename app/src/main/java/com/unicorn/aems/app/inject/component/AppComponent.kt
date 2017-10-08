package com.ivotai.kotlindemo.app.inject.component

import com.ivotai.kotlindemo.app.inject.module.BoxModule
import com.ivotai.kotlindemo.app.inject.module.NetworkModule
import com.ivotai.kotlindemo.app.inject.scope.AppScope
import com.unicorn.aems.airport.inject.component.AirportComponent
import dagger.Component


@AppScope
@Component(modules = arrayOf(NetworkModule::class, BoxModule::class))
interface AppComponent {

    // sub component
    fun getAirportComponent(): AirportComponent

}