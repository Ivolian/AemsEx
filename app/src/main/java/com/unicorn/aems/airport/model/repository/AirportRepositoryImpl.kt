package com.unicorn.aems.airport.model.repository

import com.unicorn.aems.airport.model.entity.Airport
import com.unicorn.aems.airport.model.api.AirportApi
import com.unicorn.aems.airport.model.entity.AirportResponse
import com.unicorn.aems.airport.model.entity.Airport_
import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime


class AirportRepositoryImpl(private val api: AirportApi, private val box: Box<Airport>) : AirportRepository {

    override val lastUpdateDate: String
        get() {
            val airport = box.query()
                    .equal(Airport_.deleted, 0)
                    .order(Airport_.updateDate)
                    .build()
                    .findFirst()
            return if (airport === null) "19000101000000"
            else DateTime(airport.updateDate).toString("yyyyMMddHHmmss")
        }

    override fun checkForUpdates(): Observable<AirportResponse> =
            api.getAppDataConfig(lastUpdateDate, "airportInfo")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


    override fun query(keyword: String): Observable<List<Airport>> {
        val query = box.query()
                .equal(Airport_.deleted, 0)
                .contains(Airport_.name, keyword)
                .or()
                .equal(Airport_.deleted, 0)
                .contains(Airport_.pinyin, keyword.toUpperCase())
                .build()
        return RxQuery.observable(query)
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun put(airports: List<Airport>) {
        box.put(airports)
    }

    override fun defaultAirport(): Airport? = box.query()
            .equal(Airport_.deleted, 0)
            .build()
            .findFirst()

}

