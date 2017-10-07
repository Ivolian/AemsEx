package com.unicorn.aems.airport.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.ivotai.kotlindemo.movie.model.entity.Airport
import com.unicorn.aems.R
import com.unicorn.aems.app.base.view.VH
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.index_airport.*
import kotlinx.android.synthetic.main.item_airport.*


import me.yokeyword.indexablerv.IndexableAdapter


class AirportAdapter(context: Context) : IndexableAdapter<Airport>() {

    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateTitleViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.index_airport, parent, false)
        return VH(view)
    }

    override fun onCreateContentViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_airport, parent, false)
        return VH(view)
    }

    override fun onBindTitleViewHolder(holder: RecyclerView.ViewHolder, indexTitle: String) {
        val indexViewHolder = holder as VH
        indexViewHolder.tvIndex!!.text = indexTitle
    }

    override fun onBindContentViewHolder(holder: RecyclerView.ViewHolder, airport: Airport) {
        val itemViewHolder = holder as VH
        itemViewHolder.tvAirport.text = airport.name
    }

}