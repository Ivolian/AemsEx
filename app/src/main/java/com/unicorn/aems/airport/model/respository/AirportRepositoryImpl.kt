package com.ivotai.kotlindemo.movie.model.respository

import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.ivotai.kotlindemo.movie.model.entity.Airport_
import com.unicorn.aems.airport.model.api.AirportApi
import com.unicorn.aems.airport.model.entity.AirportResponse
import io.objectbox.Box
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AirportRepositoryImpl(private val api: AirportApi, private val box: Box<Airport>) : AirportRepository {

    override fun checkUpdate(): Single<AirportResponse> {
        return api.getAppDataConfig(getLastUpdateDate(), "airportInfo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun get(query: String): Single<List<Airport>> {
        return object : Single<List<Airport>>() {
            override fun subscribeActual(observer: SingleObserver<in List<Airport>>) {
            }
        }

    }

    override fun getLastUpdateDate(): String {
        val builder = box.query()
        val query = builder.equal(Airport_.deleted, 0).build()
        val airport = query.findFirst()
        return airport?.updateDate ?: "19000101000000"
    }

}

