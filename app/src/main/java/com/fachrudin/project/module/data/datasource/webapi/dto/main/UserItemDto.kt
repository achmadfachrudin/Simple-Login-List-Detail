package com.fachrudin.project.module.data.datasource.webapi.dto.main

import com.fachrudin.project.module.data.datasource.webapi.dto.BaseApiDto
import com.google.gson.annotations.SerializedName

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class UserItemDto : BaseApiDto() {
    @SerializedName("id")
    val id: String? = null
    @SerializedName("first_name")
    val first_name: String? = null
    @SerializedName("last_name")
    val last_name: String? = null
    @SerializedName("avatar")
    val avatar: String? = null
}