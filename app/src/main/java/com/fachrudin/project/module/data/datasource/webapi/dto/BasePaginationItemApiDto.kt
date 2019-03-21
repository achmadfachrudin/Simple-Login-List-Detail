package com.fachrudin.project.module.data.datasource.webapi.dto

import com.google.gson.annotations.SerializedName

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
open class BasePaginationItemApiDto<T> : BasePaginationApiDto() {

    @SerializedName("items")
    var mItems: List<T>? = ArrayList()
}