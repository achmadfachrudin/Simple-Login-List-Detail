package com.fachrudin.project.core.common.protocol

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface Logger {

    fun verbose(message: String): Any

    fun info(message: String): Any

    fun debug(message: String): Any

    fun warning(message: String): Any

    fun error(message: String): Any

    fun error(message: String, throwable: Throwable): Any
}
