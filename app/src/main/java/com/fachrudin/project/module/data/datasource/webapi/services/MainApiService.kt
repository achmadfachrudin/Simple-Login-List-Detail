package com.fachrudin.project.module.data.datasource.webapi.services

import com.fachrudin.project.module.data.datasource.webapi.dto.main.UserListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface MainApiService {

    @GET("api/users")
    fun getUserList(@Query("page") page: Int): Call<UserListDto>
}