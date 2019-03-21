package com.fachrudin.project.app.internal.di

import com.fachrudin.project.app.internal.module.MainFeatureModule
import com.fachrudin.project.app.presentation.*
import com.fachrudin.project.core.di.FeatureScope
import com.fachrudin.project.core.di.components.AppComponent
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.module.base.di.components.MainRepositoryComponent

import dagger.Component

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@FeatureScope
@Component(dependencies = [AppComponent::class, MainRepositoryComponent::class],
    modules = [MainFeatureModule::class])
interface MainFeatureComponent : FeatureComponent {
    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: UserListFragment)
    fun inject(activity: UserDetailActivity)
}
