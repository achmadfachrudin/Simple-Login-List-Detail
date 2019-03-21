package com.fachrudin.project.core.di

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 *
 * ComponentProvider
 * Variance on [ComponentOwner] if class has more than one component
 */
interface ComponentProvider {

    val components: MutableMap<Class<out Any>, in Any>

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getComponent(classOfT: Class<T>): T {
        return (components.get(classOfT) as? T) ?:
                throw IllegalArgumentException("No component found for class ${classOfT}")
    }

    fun <T : Any> addComponent(component: T, classOfT: Class<T>) {
        components.put(classOfT, component)
    }
}
