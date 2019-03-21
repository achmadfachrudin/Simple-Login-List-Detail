package com.fachrudin.project.module.data.datasource.webapi.services

import com.fachrudin.project.module.data.datasource.webapi.dto.sample.SampleTestDto
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface SampleApiService {

    @GET("posts/1")
    fun getSampleTest(): Call<SampleTestDto>
}