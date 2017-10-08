package com.unicorn.aems.user.model.repository

import com.unicorn.aems.user.model.entity.LoginInfo
import com.unicorn.aems.user.model.entity.SessionInfo
import io.reactivex.Observable


interface UserRepository {


    fun login(account: String, pwd: String): Observable<SessionInfo>
    fun getLoginInfo():LoginInfo?
    fun putLoginInfo(loginInfo: LoginInfo)

}