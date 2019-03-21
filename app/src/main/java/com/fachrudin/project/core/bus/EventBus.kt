package com.fachrudin.project.core.bus

import androidx.lifecycle.LifecycleOwner
import com.fachrudin.project.core.internal.EventSubscriberPair
import com.fachrudin.project.core.internal.PublisherMap
import com.fachrudin.project.core.internal.getOrCreate

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
object EventBus {

    private val mPublisherMap: PublisherMap

    init {
        this.mPublisherMap = PublisherMap()
    }

    @Suppress("UNCHECKED_CAST")
    fun subscribe(lifecycle: LifecycleOwner, subscriber: EventSubscriber<out Any>, classOfT: Class<out Any>) {
        val publisher = mPublisherMap.getOrCreate(lifecycle)
        publisher.subscribe(lifecycle, Pair(classOfT, subscriber) as EventSubscriberPair)
    }

    fun send(event: Any) {
        for (publisher in mPublisherMap.values) {
            publisher.sendEvent(event)
        }
    }

    fun post(event: Any) {
        for (publisher in mPublisherMap.values) {
            publisher.postEvent(event)
        }
    }
}
