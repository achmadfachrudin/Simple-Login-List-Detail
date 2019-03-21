package com.fachrudin.project.module.data.datasource.webapi.dto.sample

import com.fachrudin.project.module.data.datasource.webapi.dto.BaseApiDto
import com.google.gson.annotations.SerializedName

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class SampleTestDto : BaseApiDto() {
    @SerializedName("userId")
    val userId: String? = null
    @SerializedName("id")
    val id: String? = null
    @SerializedName("title")
    val title: String? = null
    @SerializedName("body")
    val body: String? = null
}