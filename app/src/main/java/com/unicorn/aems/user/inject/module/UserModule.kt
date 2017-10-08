package com.unicorn.aems.user.inject.module

import com.unicorn.aems.airport.inject.scope.UserScope
import com.unicorn.aems.user.model.api.UserApi
import com.unicorn.aems.user.model.entity.LoginInfo
import com.unicorn.aems.user.model.repository.UserRepository
import com.unicorn.aems.user.model.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import io.objectbox.Box
import io.objectbox.BoxStore
import retrofit2.Retrofit
import javax.inject.Named


@Module
class UserModule {

    @UserScope
    @Provides
    fun box(boxStore: BoxStore): Box<LoginInfo> = boxStore.boxFor(LoginInfo::class.java)

    @UserScope
    @Provides
    fun api(@Named(value = "default") retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @UserScope
    @Provides
    fun repository(api: UserApi, box: Box<LoginInfo>): UserRepository = UserRepositoryImpl(api, box)

}
