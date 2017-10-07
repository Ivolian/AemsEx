package com.unicorn.aems.airport.view

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ivotai.kotlindemo.app.ComponentsHolder
import com.ivotai.kotlindemo.app.base.view.BaseAct
import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.ivotai.kotlindemo.movie.presenter.AirportPresenter
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.aems.R
import com.unicorn.aems.airport.view.adapter.AirportAdapter
import kotlinx.android.synthetic.main.act_airport.*
import me.yokeyword.indexablerv.IndexableLayout


class AirportAct : BaseAct(), AirportView {


    // ===== layoutResId =====

    override val layoutResId = R.layout.act_airport


    // ===== bindPresenter =====

    private val presenter by lazy { AirportPresenter(this, ComponentsHolder.airportComponent.getRepository()) }

    override fun bindPresenter(savedInstanceState: Bundle?) {
        RxTextView.afterTextChangeEvents(etSearch)
                .map { it.editable().toString().trim() }
                .subscribe { presenter.onTextChange(it) }
//        RxView.clicks(btnUpdate)
//                .throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(object :io.reactivex.functions.Consumer<Any>{
//                    override fun accept(t: Any) {
//                        presenter.onBtnClick()
//                    }
//                })
    }


    override fun render(airports: List<Airport>) {
        adapter.setDatas(airports)
    }


//    @BindColor(R.color.md_grey_50)
//    internal var grey50: Int = 0
//
//    @BindColor(R.color.md_grey_300)
//    internal var grey300: Int = 0
//
//    @BindColor(R.color.md_grey_400)
//    internal var grey400: Int = 0
//

//    @BindColor(R.color.colorPrimary)
//    internal var colorPrimary: Int = 0

    private val adapter by lazy { AirportAdapter(this) }

    override fun initViews(savedInstanceState: Bundle?) {
        clicksFinish(tvCancel)

            presenter.onViewCreated()
        initEtSearch()
        initIndexableLayout()
    }


    private fun initEtSearch() {
        // 背景
        val etSearchBg = GradientDrawable()
//        etSearchBg.cornerRadius = ConvertUtils.dp2px(5f).toFloat()
//        etSearchBg.setStroke(1, grey300)
//        etSearchBg.setColor(grey50)
//        etSearch!!.background = etSearchBg
//        etSearch!!.setHintTextColor(grey400)

//        // 查询图标
//        val search = IconicsDrawable(this)
//                .icon(Ionicons.Icon.ion_ios_search)
//                .colorRes(R.color.colorPrimary)
//                .sizeDp(17)
//        etSearch!!.compoundDrawablePadding = ConvertUtils.dp2px(8f)
//        etSearch!!.setCompoundDrawables(search, null, null, null)

        // 查询


    }


    private fun initIndexableLayout() {
        indexableLayout.setLayoutManager(LinearLayoutManager(this))
        indexableLayout.setCompareMode(IndexableLayout.MODE_ALL_LETTERS)
        setCenterOverlay()
        addItemDecoration()
        indexableLayout.setAdapter(adapter)
        setOnItemContentClickListener()
//
//        // 加载列表
//        airportService!!.listUsed()
//                // indexableAdapter 有个 bug 不得不过滤下
//                .filter({ airports -> airports.size() !== 0 })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ airports -> airportAdapter!!.setDatas(airports) })
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

    /**
     * setOnItemContentClickListener.
     */
    private fun setOnItemContentClickListener() {
//        airportAdapter!!.setOnItemContentClickListener({ v, originalPosition, currentPosition, airport ->
//            if (originalPosition >= 0) {
//                RxBus.checkUpdate().post(RxBusTag.AIRPORT_SELECTED, airport)
//                finish()
//            }
//        })
    }

}
