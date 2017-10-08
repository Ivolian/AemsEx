package com.ivotai.kotlindemo.movie.model.respository

import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.ivotai.kotlindemo.movie.model.entity.Airport_
import com.unicorn.aems.airport.model.api.AirportApi
import com.unicorn.aems.airport.model.entity.AirportResponse
import com.unicorn.aems.airport.model.respository.AirportRepository
import io.objectbox.Box
//import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime

// todo 4 rx or plain
class AirportRepositoryImpl(private val api: AirportApi, private val box: Box<Airport>) : AirportRepository {

    override fun checkUpdate(): Observable<AirportResponse> {
        return api.getAppDataConfig(lastUpdateDate, "airportInfo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
    // todo 3 线程转换

    // todo 5 put 的更新依据

    // 测试
    // 当数据为空返回空ArrayList
    // todo check 逻辑删除
//    override fun get(query: String): Observable<List<Airport>> = RxQuery.observable(box.query()
//            .equal(Airport_.deleted, 0)
//            .contains(Airport_.name, query)
//            .or()
//            .equal(Airport_.deleted, 0)
//            .contains(Airport_.pinyin, query.toUpperCase())
//            .build())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())

    // 测试
    // 当数据为空时返回 19000101000000
    // todo 有数据时返回时间早了 8 小时
    override val lastUpdateDate: String
        get() {
            val first = box.query()
                    .equal(Airport_.deleted, 0)
                    .order(Airport_.updateDate)
                    .build()
                    .findFirst()
            return if (first === null) "19000101000000"
            else DateTime(first.updateDate).toString("yyyyMMddHHmmss")
        }

    override fun put(airports: List<Airport>) {
        box.put(airports)
    }

}

