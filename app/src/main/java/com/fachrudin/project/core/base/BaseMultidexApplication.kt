package com.fachrudin.project.core.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.multidex.MultiDexApplication
import com.fachrudin.project.core.di.ComponentProvider
import com.fachrudin.project.core.di.components.AppComponent
import com.fachrudin.project.core.internal.AppComponentOwner

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class BaseMultidexApplication :
        MultiDexApplication(),
        LifecycleOwner,
        AppComponentOwner,
        ComponentProvider {

    final override val component: AppComponent by lazy { buildComponent() }

    internal val mLifecycleRegistry = LifecycleRegistry(this)
    final override val components: HashMap<Class<out Any>, in Any>

    init {
        mLifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
        components = HashMap()
    }

    override fun getLifecycle(): LifecycleRegistry {
        return mLifecycleRegistry
    }

    override fun onCreate() {
        super.onCreate()
        mLifecycleRegistry.markState(Lifecycle.State.STARTED)
    }
}