package com.ivotai.kotlindemo.movie.presenter

import com.blankj.utilcode.util.ToastUtils
import com.github.promeg.pinyinhelper.Pinyin
import com.ivotai.kotlindemo.movie.model.respository.AirportRepository
import com.unicorn.aems.airport.view.AirportView
import com.unicorn.aems.app.base.presenter.BasePresenter
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import org.joda.time.DateTime


class AirportPresenter(private var view: AirportView, private var repository: AirportRepository) : BasePresenter() {

    override fun onViewCreated() {
        load()
    }

    fun onBtnClick() = with(repository) {
        checkUpdate().subscribeBy(
                onSuccess = {
                    val airports = it.list
                    airports.forEach{
                        it.pinyin = Pinyin.toPinyin(it.name,"")
                        it.updateDate = DateTime().toString("yyyyMMddHHmmss")
                    }
                    put(airports)
                    load()
                },
                onError = {}
        )
    }

    // 加载并显示列表
    private fun load() = repository.get().subscribeBy { view.render(it) }


    fun onQuery(query: String) {
    load(query)
    }

    private fun load(query: String) = repository.get(query).subscribeBy(
            onSuccess = { view.render(it) },
            onError = { ToastUtils.showShort("加载机场列表失败") }
    )


}