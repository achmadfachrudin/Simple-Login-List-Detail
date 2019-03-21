package com.fachrudin.project.module.base.di.modules

import dagger.Module
import dagger.Provides
import com.fachrudin.project.core.di.ApplicationScope
import com.fachrudin.project.core.di.modules.ApiModule
import com.fachrudin.project.core.webapi.ServiceFactory
import com.fachrudin.project.core.webapi.WebApi
import com.fachrudin.project.core.webapi.impl.RetrofitServiceFactory
import com.fachrudin.project.module.data.datasource.webapi.SampleNetworkInterceptor
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@Module
class SampleApiModule(api: WebApi) : ApiModule(api) {

    @Provides
    @ApplicationScope
    internal fun provideServiceFactory(interceptor: Interceptor,
                                       adapterFactory: CallAdapter.Factory,
                                       converterFactory: Converter.Factory): ServiceFactory {
        return RetrofitServiceFactory(api, interceptor, adapterFactory, converterFactory)
    }

    @Provides
    @ApplicationScope
    internal fun provideInterceptor(): Interceptor {
        return SampleNetworkInterceptor(api)
    }

    @Provides
    @ApplicationScope
    internal fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @ApplicationScope
    internal fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}
