package com.unicorn.aems.user.presenter

import com.unicorn.aems.app.base.presenter.BasePresenter
import com.unicorn.aems.user.model.repository.UserRepository
import com.unicorn.aems.user.view.LoginView
import io.reactivex.rxkotlin.subscribeBy


class LoginPresenter(var view: LoginView, var repository: UserRepository) : BasePresenter() {

    override fun onViewCreated() {
    }

    fun onLoginBtnClick() = with(view) {
        var account = "admin"
        var pwd = "12345"

        showMask()
        repository.login(account, pwd).subscribeBy(
                onNext = {
                    hideMask()
                    var jSessionId = it.jsessionid
                    if (jSessionId==null){
                        // showUpdateDialog
                    }else{


                    }
                },
                onError = {
                    hideMask()
                }
        )
    }

}