package com.unicorn.aems.user.inject.component

import com.unicorn.aems.user.inject.module.UserModule
import com.unicorn.aems.airport.inject.scope.AirportScope
import com.unicorn.aems.airport.inject.scope.UserScope
import com.unicorn.aems.airport.model.repository.AirportRepository
import com.unicorn.aems.user.model.repository.UserRepository
import dagger.Subcomponent


@UserScope
@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun getRepository(): UserRepository

}