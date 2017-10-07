package com.ivotai.kotlindemo.movie.presenter

import com.blankj.utilcode.util.ToastUtils
import com.ivotai.kotlindemo.movie.model.respository.AirportRepository
import com.ivotai.kotlindemo.movie.view.AirportView
import com.unicorn.aems.app.base.presenter.BasePresenter
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy


class AirportPresenter(private var view: AirportView, private var repository: AirportRepository) : BasePresenter() {

    override fun onViewCreated() {
    }

    fun onUpdate() {
        subscriptions += repository.checkUpdate().subscribeBy(
                onSuccess = {
                    // todo
                    view.render(it)
                },
                onError = { ToastUtils.showShort("更新机场列表失败") }
        )
    }


    fun onQuery(query: String) {
        subscriptions += load(query)
    }

    private fun load(query: String) = repository.get(query).subscribeBy(
            onSuccess = { view.render(it) },
            onError = { ToastUtils.showShort("加载机场列表失败") }
    )


}