package com.fachrudin.project.core.webapi

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface ServiceFactory {

    fun <T> createService(service: Class<T>): T
}