package com.fachrudin.project.core.bus

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface EventSubscriber<T> {

    fun onEvent(event: T)
}
