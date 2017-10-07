package com.ivotai.kotlindemo.movie.inject.module

import com.ivotai.kotlindemo.movie.inject.scope.AirportScope
import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.unicorn.aems.airport.model.api.AirportApi
import dagger.Module
import dagger.Provides
import io.objectbox.Box
import io.objectbox.BoxStore
import retrofit2.Retrofit
import javax.inject.Named


@Module
class AirportModule {

    @AirportScope
    @Provides
    fun box(boxStore: BoxStore): Box<Airport> = boxStore.boxFor(Airport::class.java)

    @AirportScope
    @Provides
    fun api(@Named(value = "apk") retrofit: Retrofit): AirportApi = retrofit.create(AirportApi::class.java)


//    @AirportScope
//    @Provides
//    fun repository(box: Box<Airport>): AirportRepository = AirportRepositoryImpl(box)
//
}