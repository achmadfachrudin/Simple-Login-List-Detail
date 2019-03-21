package com.fachrudin.project.core.bus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fachrudin.project.core.internal.EventSubscriberPair
import com.fachrudin.project.core.internal.LifecycleUnsubscribeCallback

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
internal class BusPublisher : LiveData<Any>() {

    private val mBusSubscriber = BusSubscriber()
    internal var onUnsubscribe: LifecycleUnsubscribeCallback? = null

    fun sendEvent(event: Any) {
        value = event
    }

    fun postEvent(event: Any) {
        postValue(event)
    }

    override fun removeObserver(observer: Observer<in Any>) {
        super.removeObserver(observer)
        if (observer is BusSubscriber) {
            observer.clear()
        }
    }

    override fun removeObservers(owner: LifecycleOwner) {
        super.removeObservers(owner)
        onUnsubscribe?.invoke(owner)
    }

    fun subscribe(lifecycle: LifecycleOwner, eventSubscriber: EventSubscriberPair) {
        mBusSubscriber.add(eventSubscriber)
        observe(lifecycle, mBusSubscriber)
    }
}
