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
        load("")
    }

    fun onAirportSelected(airport: Airport) {

    }

    fun onBtnClick() = with(repository) {
        checkForUpdates().map {
            it.list.forEach({
                it.pinyin = Pinyin.toPinyin(it.name, "")
                // todo 有数据时返回时间早了 8 小时
                    it.updateDate = Date()
            })
            it.list
        }
                .subscribeBy(
                        onNext = {
                            put(it)
//                            load("")
                        },
                        onError = {}
                )
    }

    // 加载并显示列表


    fun onTextChange(text: String) {
        load(text)
    }

    private fun load(query: String) = repository.query(query).subscribeBy(
            onNext = {
                ""
                view.render(it)
            },
            onError = {
                ToastUtils.showShort("加载机场列表失败")
            }
    )


}