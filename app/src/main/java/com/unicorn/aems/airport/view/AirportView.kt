package com.unicorn.aems.airport.view

import com.unicorn.aems.airport.model.entity.Airport


interface AirportView {

    fun render(airports: List<Airport>)

}