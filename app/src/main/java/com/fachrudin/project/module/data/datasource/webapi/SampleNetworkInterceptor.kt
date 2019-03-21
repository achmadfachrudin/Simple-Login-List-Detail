package com.fachrudin.project.module.data.datasource.webapi

import android.util.Log
import com.fachrudin.project.core.webapi.WebApi
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class SampleNetworkInterceptor(val api: WebApi) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (api.enableLogging) {
            Log.d("REQUEST","request: " +request.body())
        }

        return chain.proceed(request)
    }
}