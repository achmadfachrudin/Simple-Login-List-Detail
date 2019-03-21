package com.fachrudin.project.core.config

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface MutableConfig : Config {

    fun <T> set(prop: ConfigProperties<T>, value: T)
}
