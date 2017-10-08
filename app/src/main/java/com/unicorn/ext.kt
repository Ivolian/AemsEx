package com.unicorn

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import com.ivotai.kotlindemo.app.base.view.BaseAct


fun BaseAct.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)
