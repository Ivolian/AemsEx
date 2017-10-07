package com.ivotai.kotlindemo.app.base.view

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer


// 继承 LayoutContainer，支持 kotlin 拓展
class BVH(view: View) : BaseViewHolder(view), LayoutContainer {

    override val containerView: View? = itemView

}