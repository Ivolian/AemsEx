package com.unicorn.aems.airport.view

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.ivotai.kotlindemo.app.ComponentsHolder
import com.ivotai.kotlindemo.app.base.view.BaseAct
import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.jakewharton.rxbinding2.view.RxView
import com.unicorn.aems.airport.presenter.AirportPresenter
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.aems.R
import com.unicorn.aems.airport.view.adapter.AirportAdapter
import com.unicorn.color
import kotlinx.android.synthetic.main.act_airport.*
import me.yokeyword.indexablerv.IndexableLayout
import java.util.concurrent.TimeUnit


class AirportAct : BaseAct(), AirportView {

    override val layoutResId = R.layout.act_airport

    private val presenter = AirportPresenter(this, ComponentsHolder.airportComponent.getRepository())

    // after super.onCreate this could be use as context , so adapter should be call after that
    private val adapter by lazy { AirportAdapter(this) }


    override fun bindPresenter() {
        RxTextView.afterTextChangeEvents(etQuery)
                .map { event -> event.editable().toString().trim() }
                .subscribe { presenter.onKeywordChange(it) }
        RxView.clicks(btnUpdate)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe { presenter.onBtnClick() }
        adapter.setOnItemContentClickListener { _, _, _, airport -> presenter.onAirportSelected(airport) }
        presenter.onViewCreated()
    }

    override fun render(airports: List<Airport>) {
        adapter.setDatas(airports)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        initEtQuery()
        initIndexableLayout()
        clicksFinish(cancel)
    }


    private fun initEtQuery() {
        val bg = GradientDrawable()
        bg.cornerRadius = ConvertUtils.dp2px(5f).toFloat()
        bg.setStroke(1, color(R.color.md_grey_300))
        bg.setColor(color(R.color.md_grey_100))
        etQuery.background = bg
        etQuery.setHintTextColor(color(R.color.md_grey_400))

//        // 查询图标
//        val search = IconicsDrawable(this)
//                .icon(Ionicons.Icon.ion_ios_search)
//                .colorRes(R.color.colorPrimary)
//                .sizeDp(17)
//        etSearch!!.compoundDrawablePadding = ConvertUtils.dp2px(8f)
//        etSearch!!.setCompoundDrawables(search, null, null, null)

    }


    private fun initIndexableLayout() {
        indexableLayout.setLayoutManager(LinearLayoutManager(this))
        indexableLayout.setCompareMode(IndexableLayout.MODE_ALL_LETTERS)
        setCenterOverlay()
        addItemDecoration()
        indexableLayout.setAdapter(adapter)
    }

    private fun setCenterOverlay() {
//        indexableLayout!!.setOverlayStyle_Center()
//        indexableLayout!!.getmCenterOverlay().setBackgroundColor(Color.TRANSPARENT)
//        indexableLayout!!.getmCenterOverlay().setTextColor(colorPrimary)
//        indexableLayout!!.getmCenterOverlay().setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50)
    }

    /**
     * addItemDecoration.
     */
    private fun addItemDecoration() {
//        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
//        paint.strokeWidth = 1f
//        paint.color = grey300
//        val itemDecoration = HorizontalDividerItemDecoration.Builder(this).paint(paint).build()
//        indexableLayout!!.recyclerView.addItemDecoration(itemDecoration)
    }


}
