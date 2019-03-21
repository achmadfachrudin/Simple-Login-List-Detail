package com.fachrudin.project.app.internal.di

import com.fachrudin.project.app.BaseApp
import com.fachrudin.project.core.di.ApplicationScope
import com.fachrudin.project.core.di.components.AppComponent

import dagger.Component

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@ApplicationScope
@Component(modules = [BaseAppModule::class])
interface BaseAppComponent : AppComponent {
    fun inject(app: BaseApp)
}
