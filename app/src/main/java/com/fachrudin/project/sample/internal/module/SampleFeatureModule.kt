package com.fachrudin.project.sample.internal.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.fachrudin.project.module.base.di.ViewModelKey
import com.fachrudin.project.sample.presentation.viewmodel.*
import com.fachrudin.project.sample.provider.MapViewModelProviderFactory
import com.fachrudin.project.module.biz.repositories.SampleRepository
import com.fachrudin.project.core.di.FeatureScope
import com.fachrudin.project.core.di.modules.FeatureModule

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import com.fachrudin.project.module.biz.interactors.sample.GetSampleInteractor

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@Module
class SampleFeatureModule : FeatureModule() {
    /*
    ViewModel
     */
    @Provides
    @FeatureScope
    internal fun provideViewModelFactory(factory: MapViewModelProviderFactory): ViewModelProvider.Factory {
        return factory
    }

    @Provides
    @FeatureScope
    @IntoMap
    @ViewModelKey(SampleViewModel::class)
    internal fun provideSampleViewModel(viewModel: SampleViewModel): ViewModel {
        return viewModel
    }

    /*
    Interactor
     */
    @Provides
    @FeatureScope
    internal fun provideGetSampleInteractor(repository: SampleRepository): GetSampleInteractor {
        return GetSampleInteractor(repository)
    }
}
