package com.fachrudin.project.core.exceptions

import retrofit2.HttpException

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class ResponseException(message: String?, cause: HttpException? = null) :
        DataStreamException(message, cause) {

    constructor(message: String?) : this(message, null)
}
