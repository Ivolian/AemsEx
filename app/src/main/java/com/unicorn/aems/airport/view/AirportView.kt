package com.ivotai.kotlindemo.movie.view

import com.ivotai.kotlindemo.movie.model.entity.Airport


interface AirportView {

    fun render(airports: List<Airport>)

}