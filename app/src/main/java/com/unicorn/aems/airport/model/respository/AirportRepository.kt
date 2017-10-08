package com.unicorn.aems.airport.model.respository

import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.unicorn.aems.airport.model.entity.AirportResponse
import io.reactivex.Observable


interface AirportRepository {

    val lastUpdateDate: String
    fun checkForUpdates(): Observable<AirportResponse>
    fun query(keyword: String): Observable<List<Airport>>
    fun put(airports: List<Airport>)
    fun defaultAirport(): Airport?

}
