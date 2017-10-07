package com.ivotai.kotlindemo.movie.model.respository

import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.unicorn.aems.airport.model.entity.AirportResponse
import io.reactivex.Single


interface AirportRepository {

     fun checkUpdate(): Single<AirportResponse>
     fun get(query:String): Single<List<Airport>>
     fun getLastUpdateDate():String

}
