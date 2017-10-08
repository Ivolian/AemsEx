package com.unicorn.aems.airport.model.respository

import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.unicorn.aems.airport.model.entity.AirportResponse
import io.reactivex.Observable
import io.reactivex.Single


interface AirportRepository {

    val lastUpdateDate: String
    fun checkUpdate(): Observable<AirportResponse>
//    fun get(query: String): Observable<List<Airport>>
    fun put(airports: List<Airport>)

}
