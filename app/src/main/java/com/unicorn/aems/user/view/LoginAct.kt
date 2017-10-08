package com.unicorn.aems.user.view

import android.os.Bundle
import com.ivotai.kotlindemo.app.ComponentsHolder
import com.ivotai.kotlindemo.app.base.view.BaseAct
import com.jakewharton.rxbinding2.view.RxView
import com.unicorn.aems.R
import com.unicorn.aems.user.presenter.LoginPresenter
import kotlinx.android.synthetic.main.act_login.*
import java.util.concurrent.TimeUnit


class LoginAct : BaseAct(), LoginView {

    override val layoutResId = R.layout.act_login

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun bindViewEvents() {
    }

    private val presenter = LoginPresenter(this, ComponentsHolder.userComponent.getRepository())

    override fun bindPresenterEvent() {
        RxView.clicks(btnLogin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { presenter.onLoginBtnClick() }

    }

    override fun showMask() {
    }

    override fun hideMask() {
    }

    override fun showError() {
    }

}
