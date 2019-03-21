package com.fachrudin.project.core.webapi.impl

import com.fachrudin.project.core.webapi.ServiceFactory
import com.fachrudin.project.core.webapi.WebApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class RetrofitServiceFactory(val api: WebApi) : ServiceFactory {

    private var interceptor: Interceptor? = null
    private var adapterFactory: CallAdapter.Factory? = null
    private var converterFactory: Converter.Factory? = null

    constructor(api: WebApi, interceptor: Interceptor?,
                adapterFactory: CallAdapter.Factory?,
                converterFactory: Converter.Factory?) : this(api) {
        this.interceptor = interceptor
        this.adapterFactory = adapterFactory
        this.converterFactory = converterFactory
    }

    override fun <T> createService(service: Class<T>): T {
        val logging = HttpLoggingInterceptor()
        logging.level = if (api.enableLogging) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(api.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(api.readTimeout, TimeUnit.SECONDS)
                .addInterceptor(logging)
        if (interceptor != null) {
            clientBuilder.addNetworkInterceptor(interceptor!!)
        }
        val retrofitBuilder = Retrofit.Builder()
                .client(clientBuilder.build())
                .baseUrl(api.baseUrl)
        if (adapterFactory != null) {
            retrofitBuilder.addCallAdapterFactory(adapterFactory!!)
        }
        if (converterFactory != null) {
            retrofitBuilder.addConverterFactory(converterFactory!!)
        }
        return retrofitBuilder.build().create(service)
    }
}
