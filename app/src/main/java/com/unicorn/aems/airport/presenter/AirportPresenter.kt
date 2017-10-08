package com.unicorn.aems.airport.presenter

import com.blankj.utilcode.util.ToastUtils
import com.github.promeg.pinyinhelper.Pinyin
import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.unicorn.aems.airport.model.respository.AirportRepository
import com.unicorn.aems.airport.view.AirportView
import com.unicorn.aems.app.base.presenter.BasePresenter
import io.reactivex.rxkotlin.subscribeBy
import java.util.*


class AirportPresenter(private var view: AirportView, private var repository: AirportRepository) : BasePresenter() {

    override fun onViewCreated() {
        load()
    }

    fun onAirportSelected(airport: Airport) {

    }

    fun onBtnClick() = repository.checkForUpdates()
            .map {
                val airports = it.list
                airports.forEach { airport ->
                    airport.pinyin = Pinyin.toPinyin(airport.name, "")
                    // todo 时间早了 8 小时
                    airport.updateDate = Date()
                }
                airports
            }.subscribeBy(
            onNext = { airports ->
                if (airports.isEmpty()) {
                    ToastUtils.showShort("已是最新列表!")
                } else {
                    ToastUtils.showShort("更新机场列表成功!")
                    repository.put(airports)
                    load()
                }
            },
            onError = {
                ToastUtils.showShort("更新机场失败")
            }
    )

    fun onKeywordChange(text: String) {
        load(text)
    }

    private fun load() {
        load("")
    }

    private fun load(keyword: String) = repository.query(keyword).subscribe { view.render(it) }

}