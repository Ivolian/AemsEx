package com.unicorn.aems.user.model.repository

import com.unicorn.aems.user.model.api.UserApi
import com.unicorn.aems.user.model.entity.LoginInfo
import com.unicorn.aems.user.model.entity.SessionInfo
import io.objectbox.Box
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UserRepositoryImpl(private val api: UserApi, private val box: Box<LoginInfo>) : UserRepository {

    override fun login(account: String, pwd: String): Observable<SessionInfo> {
        return api.login(account, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLoginInfo() = box.query().build().findUnique()

    override fun putLoginInfo(loginInfo: LoginInfo) {
        box.put(loginInfo)
    }

}