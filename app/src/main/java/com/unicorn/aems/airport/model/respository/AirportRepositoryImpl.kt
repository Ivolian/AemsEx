package com.ivotai.kotlindemo.movie.model.respository

import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.ivotai.kotlindemo.movie.model.entity.Airport_
import com.unicorn.aems.airport.model.api.AirportApi
import com.unicorn.aems.airport.model.entity.AirportResponse
import com.unicorn.aems.airport.model.respository.AirportRepository
import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AirportRepositoryImpl(private val api: AirportApi, private val box: Box<Airport>) : AirportRepository {

    override fun checkUpdate(): Observable<AirportResponse> {
        return api.getAppDataConfig(lastUpdateDate, "airportInfo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun get(query: String): Observable<List<Airport>> = RxQuery.observable(box.query()
            .equal(Airport_.deleted, 0)
            .contains(Airport_.name, query)
            .contains(Airport_.pinyin, query)
            .build())



    override val lastUpdateDate = box.query()
            .equal(Airport_.deleted, 0)
            .order(Airport_.updateDate)
            .build()
            .findFirst()?.updateDate ?: "19000101000000"

    override fun put(airports: List<Airport>) {
        box.put(airports)
    }
}

