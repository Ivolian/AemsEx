package com.ivotai.kotlindemo.app.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

abstract class BaseAct : AppCompatActivity() {

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        injectDependencies()
        initViews(savedInstanceState)
        bindPresenter(savedInstanceState)
    }

    abstract val layoutResId: Int

    protected fun injectDependencies() {}

    abstract fun initViews(savedInstanceState: Bundle?)

    abstract fun bindPresenter(savedInstanceState: Bundle?)

    protected fun clicksFinish(view: View) {
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { finish() }
    }

}