package com.unicorn.aems.app.base.presenter

import com.unicorn.aems.app.base.presenter.Presenter
import io.reactivex.disposables.CompositeDisposable


abstract class BasePresenter : Presenter {

    protected val subscriptions = CompositeDisposable()

    // 生命周期结束时 dispose
    override fun onViewDestroyed(){
        subscriptions.dispose()
    }

}