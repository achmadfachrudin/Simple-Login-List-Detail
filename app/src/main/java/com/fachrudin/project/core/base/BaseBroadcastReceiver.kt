package com.fachrudin.project.core.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.fachrudin.project.core.di.ComponentOwner
import com.fachrudin.project.core.di.ComponentProvider
import com.fachrudin.project.core.di.components.AppComponent
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.core.internal.AppComponentOwner

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class BaseBroadcastReceiver : BroadcastReceiver(), ComponentOwner<FeatureComponent> {

    override val component: FeatureComponent by lazy { buildComponent() }
    private var application: BaseApplication? = null

    @Suppress("UNCHECKED_CAST")
    protected val appComponent: AppComponent
        get() = (application as? AppComponentOwner)?.component
                ?: throw IllegalStateException("Application must implements ComponentOwner<AppComponent>")

    protected fun <T : Any> getProvidedComponent(classOfT: Class<T>): T {
        return (application as? ComponentProvider)?.getComponent(classOfT)
                ?: throw IllegalStateException("Application must implements ComponentProvider")
    }

    protected abstract fun injectComponent()

    override fun onReceive(context: Context?, intent: Intent?) {
        this.application = context?.applicationContext as BaseApplication
        injectComponent()
    }
}
