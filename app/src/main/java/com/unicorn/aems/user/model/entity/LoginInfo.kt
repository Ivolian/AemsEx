package com.unicorn.aems.user.model.entity

import com.unicorn.aems.airport.model.entity.Airport
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne


@Entity
class LoginInfo(
        @Id(assignable = true)
        var _id: Long = 0,
        var account: String,
        var pwd: String
) {
    lateinit var airport: ToOne<Airport>
}


