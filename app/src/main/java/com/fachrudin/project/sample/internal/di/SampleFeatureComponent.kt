package com.fachrudin.project.sample.internal.di

import com.fachrudin.project.sample.internal.module.SampleFeatureModule
import com.fachrudin.project.core.di.FeatureScope
import com.fachrudin.project.core.di.components.AppComponent
import com.fachrudin.project.core.di.components.FeatureComponent

import dagger.Component
import com.fachrudin.project.module.base.di.components.SampleRepositoryComponent
import com.fachrudin.project.sample.presentation.SampleActivity

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@FeatureScope
@Component(dependencies = [AppComponent::class, SampleRepositoryComponent::class], modules = [SampleFeatureModule::class])
interface SampleFeatureComponent : FeatureComponent {
    fun inject(activity: SampleActivity)
}
