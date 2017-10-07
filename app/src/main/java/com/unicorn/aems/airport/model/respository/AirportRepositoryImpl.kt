package com.ivotai.kotlindemo.movie.model.respository

import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.ivotai.kotlindemo.movie.model.entity.Airport_
import com.unicorn.aems.airport.model.api.AirportApi
import com.unicorn.aems.airport.model.entity.AirportResponse
import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AirportRepositoryImpl(private val api: AirportApi, private val box: Box<Airport>) : AirportRepository {

    override fun checkUpdate(): Single<AirportResponse> {
        return api.getAppDataConfig(getLastUpdateDate(), "airportInfo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun get(query: String): Single<List<Airport>> {
        val builder = box.query()
        val query1 = builder
                .equal(Airport_.deleted, 0)
                .contains(Airport_.name, query)
                .contains(Airport_.pinyin, query)
                .build()
        return RxQuery.single(query1)
    }

    override fun get(): Observable<List<Airport>> = RxQuery.observable(box.query()
            .equal(Airport_.deleted, 0)
            .build())


    override fun getLastUpdateDate(): String {
        val builder = box.query()
        val query = builder.equal(Airport_.deleted, 0).order(Airport_.updateDate).build()
        val airport = query.findFirst()
        return airport?.updateDate ?: "19000101000000"
    }

    override fun put(airports: List<Airport>) {
        box.put(airports)
    }
}

