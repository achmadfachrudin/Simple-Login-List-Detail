package com.fachrudin.project.app.internal.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fachrudin.project.app.presentation.viewmodel.LoginViewModel
import com.fachrudin.project.app.presentation.viewmodel.ProfileViewModel
import com.fachrudin.project.app.presentation.viewmodel.UserDetailViewModel
import com.fachrudin.project.app.presentation.viewmodel.UserListViewModel

import com.fachrudin.project.module.base.di.ViewModelKey
import com.fachrudin.project.sample.provider.MapViewModelProviderFactory
import com.fachrudin.project.core.di.FeatureScope
import com.fachrudin.project.core.di.modules.FeatureModule
import com.fachrudin.project.module.biz.interactors.main.GetUserListInteractor

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import com.fachrudin.project.module.biz.repositories.MainRepository

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@Module
class MainFeatureModule : FeatureModule() {
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
    @ViewModelKey(LoginViewModel::class)
    internal fun provideLoginViewModel(viewModel: LoginViewModel): ViewModel {
        return viewModel
    }

    @Provides
    @FeatureScope
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal fun provideProfileViewModel(viewModel: ProfileViewModel): ViewModel {
        return viewModel
    }

    @Provides
    @FeatureScope
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    internal fun provideUserListViewModel(viewModel: UserListViewModel): ViewModel {
        return viewModel
    }

    @Provides
    @FeatureScope
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    internal fun provideUserDetailViewModel(viewModel: UserDetailViewModel): ViewModel {
        return viewModel
    }

    /*
    Interactor
     */
    @Provides
    @FeatureScope
    internal fun provideGetUserListInteractor(repository: MainRepository): GetUserListInteractor {
        return GetUserListInteractor(repository)
    }
}
