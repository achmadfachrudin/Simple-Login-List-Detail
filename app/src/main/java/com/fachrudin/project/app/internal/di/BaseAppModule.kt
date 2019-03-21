package com.fachrudin.project.app.internal.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import com.fachrudin.project.core.di.ApplicationScope
import com.fachrudin.project.core.di.modules.AppModule

import dagger.Module
import dagger.Provides

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@Module
class BaseAppModule(app: Application, language: String) : AppModule(app, language) {

    @Provides
    @ApplicationScope
    internal fun provideContext(): Context {
        return app
    }

    @Provides
    @ApplicationScope
    internal fun provideLocalPreferences(): SharedPreferences {
        return prefs
    }
}
