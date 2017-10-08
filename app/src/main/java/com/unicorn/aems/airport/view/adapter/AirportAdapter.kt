package com.unicorn.aems.airport.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.unicorn.aems.R
import com.unicorn.aems.airport.model.entity.Airport
import com.unicorn.aems.app.base.view.VH
import kotlinx.android.synthetic.main.index_airport.*
import kotlinx.android.synthetic.main.item_airport.*
import me.yokeyword.indexablerv.IndexableAdapter


class AirportAdapter(context: Context, private val layoutInflater: LayoutInflater = LayoutInflater.from(context)) : IndexableAdapter<Airport>() {

    override fun onCreateTitleViewHolder(parent: ViewGroup) = VH(layoutInflater.inflate(R.layout.index_airport, parent, false))

    override fun onCreateContentViewHolder(parent: ViewGroup) = VH(layoutInflater.inflate(R.layout.item_airport, parent, false))

    override fun onBindTitleViewHolder(holder: RecyclerView.ViewHolder, indexTitle: String) {
        val vh = holder as VH
        vh.tvIndex.text = indexTitle
    }

    override fun onBindContentViewHolder(holder: RecyclerView.ViewHolder, airport: Airport) {
        val vh = holder as VH
        vh.tvAirport.text = airport.name
    }

}