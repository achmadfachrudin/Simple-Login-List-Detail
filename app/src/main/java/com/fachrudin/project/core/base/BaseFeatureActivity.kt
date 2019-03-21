package com.fachrudin.project.core.base

import android.os.Bundle
import com.fachrudin.project.core.di.ComponentOwner
import com.fachrudin.project.core.di.ComponentProvider
import com.fachrudin.project.core.di.components.AppComponent
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.core.internal.AppComponentOwner

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class BaseFeatureActivity : BaseActivity(), ComponentOwner<FeatureComponent> {

    override val component: FeatureComponent by lazy { buildComponent() }

    @Suppress("UNCHECKED_CAST")
    protected val appComponent: AppComponent
        get() = (application as? AppComponentOwner)?.component ?:
                throw IllegalStateException("Application must implements ComponentOwner<AppComponent>")

    protected fun <T : Any> getProvidedComponent(classOfT: Class<T>): T {
        return (application as? ComponentProvider)?.getComponent(classOfT) ?:
                throw IllegalStateException("Application must implements ComponentProvider")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectComponent()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun injectComponent()
}
