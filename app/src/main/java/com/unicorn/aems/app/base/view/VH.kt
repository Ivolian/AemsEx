package com.unicorn.aems.app.base.view

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer


class VH(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    override val containerView: View? = itemView

}