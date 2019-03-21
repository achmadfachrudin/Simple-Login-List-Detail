package com.fachrudin.project.core.exceptions

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class RequestException(message: String?, cause: Throwable? = null) :
        DataStreamException(message, cause) {

    constructor(message: String?) : this(message, null)
}
