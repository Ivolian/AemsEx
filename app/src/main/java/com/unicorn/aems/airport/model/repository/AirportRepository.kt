package com.unicorn.aems.airport.model.repository

import com.unicorn.aems.airport.model.entity.Airport
import com.unicorn.aems.airport.model.entity.AirportResponse
import io.reactivex.Observable


interface AirportRepository {

    val lastUpdateDate: String
    fun checkForUpdates(): Observable<AirportResponse>
    fun query(keyword: String): Observable<List<Airport>>
    fun put(airports: List<Airport>)
    fun defaultAirport(): Airport?

}
