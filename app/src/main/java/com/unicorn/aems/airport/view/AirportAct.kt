package com.unicorn.aems.airport.view

import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.ivotai.kotlindemo.app.ComponentsHolder
import com.ivotai.kotlindemo.app.base.view.BaseAct
import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.iconics.IconicsDrawable
import com.unicorn.aems.R
import com.unicorn.aems.airport.presenter.AirportPresenter
import com.unicorn.aems.airport.view.adapter.AirportAdapter
import com.unicorn.color
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.act_airport.*
import me.yokeyword.indexablerv.IndexableLayout
import java.util.concurrent.TimeUnit


class AirportAct : BaseAct(), AirportView {

    override val layoutResId = R.layout.act_airport

    override fun initView(savedInstanceState: Bundle?) {
        initEtQuery()
        initIndexableLayout()
        clicksFinish(cancel)
    }

    private fun initEtQuery() = with(etQuery) {
        val bg = GradientDrawable()
        bg.cornerRadius = ConvertUtils.dp2px(5f).toFloat()
        bg.setStroke(1, color(R.color.md_grey_300))
        bg.setColor(color(R.color.md_grey_100))
        background = bg

        setHintTextColor(color(R.color.md_grey_400))

        val icon = IconicsDrawable(this@AirportAct)
                .icon(FontAwesome.Icon.faw_search)
                .colorRes(R.color.colorPrimary)
                .sizeDp(17)
        compoundDrawablePadding = ConvertUtils.dp2px(8f)
        setCompoundDrawables(icon, null, null, null)
    }

    private val adapter by lazy { AirportAdapter(this) }

    private fun initIndexableLayout() = with(indexableLayout) {
        setLayoutManager(LinearLayoutManager(this@AirportAct))
        setOverlayStyle_Center()
        setCompareMode(IndexableLayout.MODE_ALL_LETTERS)
        setAdapter(adapter)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.strokeWidth = 1f
        paint.color = color(R.color.md_grey_300)
        recyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(this@AirportAct).paint(paint).build())
    }

    override fun bindViewEvents() {
        clicksFinish(cancel)
    }

    private val presenter = AirportPresenter(this, ComponentsHolder.airportComponent.getRepository())

    override fun bindPresenterEvent() {
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

}
