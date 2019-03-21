package com.fachrudin.project.core.internal

import androidx.lifecycle.LifecycleOwner
import com.fachrudin.project.core.bus.BusPublisher
import com.fachrudin.project.core.bus.EventSubscriber
import com.fachrudin.project.core.di.ComponentOwner
import com.fachrudin.project.core.di.components.AppComponent

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 *
 * TypeDef
 * Contains alias of type and function used internally
 */
internal typealias AppComponentOwner = ComponentOwner<AppComponent>

internal typealias EventSubscriberPair = Pair<Class<out Any>, EventSubscriber<in Any>>
internal typealias LifecycleUnsubscribeCallback = ((owner: LifecycleOwner) -> Unit)
internal typealias PublisherMap = HashMap<LifecycleOwner, BusPublisher>
internal fun PublisherMap.getOrCreate(owner: LifecycleOwner): BusPublisher {
    return get(owner) ?: {
        val publisher = BusPublisher()
        publisher.onUnsubscribe = { remove(it) }
        put(owner, publisher)
        publisher
    }.invoke()
}
