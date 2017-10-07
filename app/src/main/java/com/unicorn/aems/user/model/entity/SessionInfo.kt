package com.unicorn.aems.user.model.entity


class SessionInfo(
    var currentUser: UserInfo,
    var jsessionid: String,
    var isSuccess: Boolean
)