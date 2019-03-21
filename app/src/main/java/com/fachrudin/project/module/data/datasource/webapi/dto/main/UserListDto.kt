package com.fachrudin.project.module.data.datasource.webapi.dto.main

import com.fachrudin.project.module.data.datasource.webapi.dto.BaseApiDto
import com.google.gson.annotations.SerializedName

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class UserListDto : BaseApiDto() {
    @SerializedName("page")
    val page: Int? = 0
    @SerializedName("per_page")
    val perPage: Int? = 0
    @SerializedName("total")
    val total: Int? = 0
    @SerializedName("total_pages")
    val totalPages: Int? = 0
    @SerializedName("data")
    val userItem: List<UserItemDto>? = null
}