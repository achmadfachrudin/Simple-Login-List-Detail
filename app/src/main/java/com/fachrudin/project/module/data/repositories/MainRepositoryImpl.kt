package com.fachrudin.project.module.data.repositories

import com.fachrudin.project.core.common.protocol.Mapper
import com.fachrudin.project.module.biz.entities.main.UserList
import com.fachrudin.project.module.biz.repositories.MainRepository
import com.fachrudin.project.module.data.datasource.webapi.BaseApiException
import com.fachrudin.project.module.data.datasource.webapi.dto.main.UserListDto
import com.fachrudin.project.module.data.datasource.webapi.services.MainApiService
import retrofit2.HttpException

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class MainRepositoryImpl(
    private val service: MainApiService,
    private val userMapper: Mapper<UserListDto, UserList>
) : MainRepository {

    override fun userList(page: Int): UserList {
        val response = service.getUserList(page).execute()
        if (response.isSuccessful) {
            if (response.body()!!.errorResponse != null) {
                throw BaseApiException(response.body()!!.errorResponse!!.message, HttpException(response))
            }
            return userMapper.transform(response.body()!!)
        } else {
            throw BaseApiException(response.message(), HttpException(response))
        }
    }
}