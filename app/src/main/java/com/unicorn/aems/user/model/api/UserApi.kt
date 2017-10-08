package com.unicorn.aems.user.model.api

import com.unicorn.aems.user.model.entity.SessionInfo
import io.reactivex.Observable
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @Headers("Content-Type:application/x-www-form-urlencoded", "Client-Type: Android")
    @POST("login")
    fun login(@Query("username") userName: String, @Query("password") password: String): Observable<SessionInfo>

}