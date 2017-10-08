package com.unicorn.aems.airport.inject.component

import com.unicorn.aems.airport.inject.module.AirportModule
import com.unicorn.aems.airport.inject.scope.AirportScope
import com.unicorn.aems.airport.model.respository.AirportRepository
import dagger.Subcomponent


@AirportScope
@Subcomponent(modules = arrayOf(AirportModule::class))
interface AirportComponent {

    fun getRepository(): AirportRepository

}