package com.fachrudin.project.core.exceptions

import java.io.IOException

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
open class DataStreamException : IOException {

    val DEFAULT_ERROR_MESSAGE = "Unknown error"

    companion object {
        @JvmStatic
        fun unknown() = DataStreamException(null)
    }

    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    override val message: String?
        get() = super.message ?: DEFAULT_ERROR_MESSAGE
}
