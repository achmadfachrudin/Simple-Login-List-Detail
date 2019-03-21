package com.fachrudin.project.module.base.di.components

import com.fachrudin.project.module.base.di.modules.*
import com.fachrudin.project.module.biz.repositories.SampleRepository
import com.fachrudin.project.core.di.ApplicationScope
import dagger.Component

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@ApplicationScope
@Component(modules = [SampleApiModule::class, SampleRepositoryModule::class])
interface SampleRepositoryComponent {
    fun sampleRepository(): SampleRepository
}