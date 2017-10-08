package com.ivotai.kotlindemo.app.inject.component

import com.unicorn.aems.app.inject.module.BoxModule
import com.ivotai.kotlindemo.app.inject.module.NetworkModule
import com.ivotai.kotlindemo.app.inject.scope.AppScope
import com.unicorn.aems.airport.inject.component.AirportComponent
import com.unicorn.aems.user.inject.component.UserComponent
import dagger.Component


@AppScope
@Component(modules = arrayOf(NetworkModule::class, BoxModule::class))
interface AppComponent {

    fun getAirportComponent(): AirportComponent
    fun getUserComponent(): UserComponent

}