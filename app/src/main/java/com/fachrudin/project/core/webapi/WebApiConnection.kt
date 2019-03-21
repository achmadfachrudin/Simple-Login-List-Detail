package com.fachrudin.project.core.webapi

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface WebApiConnection {

    val connectTimeout: Long
    val readTimeout: Long
    val enableLogging: Boolean
}
