package com.ivotai.kotlindemo.movie.inject.component

import com.ivotai.kotlindemo.movie.inject.module.AirportModule
import com.ivotai.kotlindemo.movie.inject.scope.AirportScope
import com.unicorn.aems.airport.model.respository.AirportRepository
import dagger.Subcomponent


@AirportScope
@Subcomponent(modules = arrayOf(AirportModule::class))
interface AirportComponent {

    fun getRepository(): AirportRepository

}