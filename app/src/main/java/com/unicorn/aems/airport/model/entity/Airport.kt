package com.ivotai.kotlindemo.movie.model.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import me.yokeyword.indexablerv.IndexableEntity
import java.util.*


@Entity
class Airport(
        @Id(assignable = true)
        var _id: Long = 0,
        var id: String,
        var name: String,
        var code: String,
        var lanUrl: String,
        var internetUrl: String,
        var updateDate: Date,
        var deleted: Int,
        var pinyin: String
) : IndexableEntity {

    override fun setFieldIndexBy(indexField: String?) {
    }

    override fun setFieldPinyinIndexBy(pinyin: String?) {
    }

    override fun getFieldIndexBy() = pinyin

}