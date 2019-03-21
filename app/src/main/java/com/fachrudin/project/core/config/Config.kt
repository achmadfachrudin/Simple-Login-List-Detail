package com.fachrudin.project.core.config

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface Config {

    fun <T> get(prop: ConfigProperties<T>): T
}
