package com.fachrudin.project.core.config

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface ConfigChangeListener {

    fun onChangeAll()

    fun <T> onChange(prop: ConfigProperties<T>, oldValue: T, newValue: T)
}
