package com.fachrudin.project.module.data.datasource.webapi.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
open class BaseApiDto : Serializable {
    @SerializedName("success")
    var success: Boolean = false
    @SerializedName("message")
    var message: String? = null
    @SerializedName("ERROR_RESPONSE")
    var errorResponse: Error? = null
    @SerializedName("summary")
    var summary: Summary? = null

    class Error(
            @SerializedName("code") var code: Int?,
            @SerializedName("title") var title: String?,
            @SerializedName("reason") var reason: String?,
            @SerializedName("message") var message: String?,
            @SerializedName("type") var type: String?,
            @SerializedName("field") var field: Any?
    )

    class Summary(@SerializedName("code") var code: String?,
                  @SerializedName("message") var message: String?,
                  @SerializedName("field") var field: Any?)
}