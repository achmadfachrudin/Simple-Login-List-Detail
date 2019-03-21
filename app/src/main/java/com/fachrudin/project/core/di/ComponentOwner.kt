package com.fachrudin.project.core.di

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 *
 * ComponentProvider
 * Interface for single component provider to inject from
 */
interface ComponentOwner<T> {

    val component: T

    @Suppress("UNCHECKED_CAST")
    fun <C : T> componentAs(classOfT: Class<C>): C = component as C

    fun buildComponent(): T
}
