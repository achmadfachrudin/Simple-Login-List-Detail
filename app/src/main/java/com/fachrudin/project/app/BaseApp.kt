package com.fachrudin.project.app

//import com.fachrudin.project.module.base.di.modules.PersistenceModule;

import android.annotation.SuppressLint
import com.fachrudin.project.BuildConfig
import com.fachrudin.project.app.internal.di.BaseAppComponent
import com.fachrudin.project.app.internal.di.BaseAppModule
import com.fachrudin.project.app.internal.di.DaggerBaseAppComponent
import com.fachrudin.project.app.utils.PrefManager
import com.fachrudin.project.core.base.BaseMultidexApplication
import com.fachrudin.project.core.di.components.AppComponent
import com.fachrudin.project.core.webapi.WebApi
import com.fachrudin.project.module.base.di.components.DaggerMainRepositoryComponent
import com.fachrudin.project.module.base.di.components.DaggerSampleRepositoryComponent
import com.fachrudin.project.module.base.di.components.MainRepositoryComponent
import com.fachrudin.project.module.base.di.components.SampleRepositoryComponent
import com.fachrudin.project.module.base.di.modules.BaseApiModule
import com.fachrudin.project.module.base.di.modules.GoogleApiModule
import com.fachrudin.project.module.base.di.modules.SampleApiModule

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class BaseApp : BaseMultidexApplication() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate() {
        super.onCreate()
        componentAs(BaseAppComponent::class.java).inject(this)
        buildParentComponent()
        PrefManager.init(this)
    }

    private fun createGoogleApi(): WebApi {
        return WebApi.Builder(BuildConfig.API_BASE, BuildConfig.API_VERSION)
            .setConnectTimeout(30)
            .setReadTimeout(30)
            .setLoggingEnabled(BuildConfig.DEBUG)
            .build()
    }

    private fun createBaseApi(): WebApi {
        return WebApi.Builder(BuildConfig.API_BASE, BuildConfig.API_VERSION)
            .setConnectTimeout(30)
            .setReadTimeout(30)
            .setLoggingEnabled(BuildConfig.DEBUG)
            .build()
    }

    private fun createSampleApi(): WebApi {
        return WebApi.Builder(BuildConfig.API_SAMPLE, BuildConfig.API_VERSION)
            .setConnectTimeout(30)
            .setReadTimeout(30)
            .setLoggingEnabled(BuildConfig.DEBUG)
            .build()
    }

    private fun buildParentComponent() {
        val googleApiModule = GoogleApiModule(createGoogleApi())
        val baseApiModule = BaseApiModule(createBaseApi())
        val sampleApiModule = SampleApiModule(createSampleApi())


        // Main Repository Component
        val mainRepositoryComponent = DaggerMainRepositoryComponent.builder()
            .baseApiModule(baseApiModule)
            .build()
        addComponent(mainRepositoryComponent, MainRepositoryComponent::class.java)

        // Sample Repository Component
        val sampleRepositoryComponent = DaggerSampleRepositoryComponent.builder()
            .sampleApiModule(sampleApiModule)
            .build()
        addComponent(sampleRepositoryComponent, SampleRepositoryComponent::class.java)
    }

    override fun buildComponent(): AppComponent {
        return DaggerBaseAppComponent.builder()
            .baseAppModule(BaseAppModule(this, "in"))
            .build()
    }
}
