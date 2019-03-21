package com.fachrudin.project.module.data.datasource.webapi

import com.fachrudin.project.module.data.datasource.webapi.dto.BaseApiDto
import com.fachrudin.project.core.exceptions.DataStreamException
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class BaseApiException(message: String?, var exception: HttpException? = null) :
    DataStreamException(message, exception) {
    private var summary: Summary? = null
        get() {
            var sum: Summary? = null
            exception?.let { httpException ->
                try {
                    httpException.response()?.errorBody()?.let { errorBody ->
                        val dto = Gson().fromJson<BaseApiDto>(errorBody.string(), BaseApiDto::class.java)
                        dto?.let {
                            if (it.errorResponse == null) {
                                it.summary?.let { summary ->
                                    val error = BaseApiDto.Error(
                                        httpException.code(),
                                        null,
                                        null,
                                        summary.message,
                                        summary.code,
                                        summary.field
                                    )
                                    dto.errorResponse = error
                                }
                            }
                            sum = Summary(dto)
                            return sum
                        }
                    }
                    httpException.response()?.body()?.let { body ->
                        val dto = body as BaseApiDto
                        dto.let {
                            if (it.errorResponse == null) {
                                it.summary?.let { summary ->
                                    val error = BaseApiDto.Error(
                                        httpException.code(),
                                        null,
                                        null,
                                        summary.message,
                                        summary.code,
                                        summary.field
                                    )
                                    dto.errorResponse = error
                                }
                            }
                            sum = Summary(dto)
                            return sum
                        }
                    }
                } catch (jse: JsonSyntaxException) {
                    // ignore
                } catch (e: Exception) {
                    // ignore
                }
            }
            return sum
        }

    var apiCode: Int? = null
        get() = summary?.code ?: field
    var apiType: String? = null
        get() = summary?.type ?: field
    var apiTitle: String? = null
        get() = summary?.title ?: field
    var apiMessage: String? = null
        get() = summary?.message ?: field
    var apiReason: String? = null
        get() = summary?.reason ?: field
    var apiObject: Any? = null
        get() = summary?.field ?: field

    class Summary(baseApiDto: BaseApiDto) {
        var code: Int? = baseApiDto.errorResponse?.code
        var type: String? = baseApiDto.errorResponse?.type
        var title: String? = baseApiDto.errorResponse?.title
        var message: String? = baseApiDto.errorResponse?.message
        var reason: String? = baseApiDto.errorResponse?.reason
        var field: Any? = baseApiDto.errorResponse?.field
    }
}