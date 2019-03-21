package com.fachrudin.project.module.data.datasource.webapi.dto

import com.google.gson.annotations.SerializedName

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
open class BasePaginationApiDto : BaseApiDto() {

    @SerializedName("currentPage")
    var mCurrentPage: Int? = null

    @SerializedName("sortStr")
    var mSortStr: String? = null

    @SerializedName("totalPages")
    var mTotalPages: Int? = null

    @SerializedName("firstPage")
    var mFirstPage: Boolean? = null

    @SerializedName("lastPage")
    var mLastPage: Boolean = true

    @SerializedName("count")
    var mCount: Int? = null

    @SerializedName("start")
    var mStart: Int? = null

    @SerializedName("size")
    var mSize: Int? = null
}