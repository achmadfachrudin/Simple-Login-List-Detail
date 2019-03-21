package com.fachrudin.project.core.bus

import androidx.lifecycle.Observer
import com.fachrudin.project.core.internal.EventSubscriberPair

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
internal class BusSubscriber : Observer<Any> {

    private val eventSubscribers = ArrayList<EventSubscriberPair>()

    override fun onChanged(t: Any?) {
        for ((subscriberType, subscriber) in eventSubscribers) {
            if (subscriberType.isInstance(t)) {
                if (t != null) {
                    subscriber.onEvent(t)
                }
            }
        }
    }

    fun add(item: EventSubscriberPair) {
        eventSubscribers.add(item)
    }

    fun clear() {
        eventSubscribers.clear()
    }
}
