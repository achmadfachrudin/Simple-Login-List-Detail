package com.fachrudin.project.module.data.repositories

import com.fachrudin.project.module.biz.entities.sample.SampleTest
import com.fachrudin.project.module.biz.repositories.SampleRepository
import com.fachrudin.project.module.data.datasource.webapi.BaseApiException
import com.fachrudin.project.module.data.datasource.webapi.dto.sample.SampleTestDto
import com.fachrudin.project.module.data.datasource.webapi.services.SampleApiService
import com.fachrudin.project.core.common.protocol.Mapper
import retrofit2.HttpException

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class SampleRepositoryImpl(
    private val service: SampleApiService,
    private val sampleMapper: Mapper<SampleTestDto, SampleTest>
) : SampleRepository {

    override fun sampleTest(): SampleTest {
        val response = service.getSampleTest().execute()
        if (response.isSuccessful) {
            if (response.body()!!.errorResponse != null) {
                throw BaseApiException(response.body()!!.errorResponse!!.message, HttpException(response))
            }
            return sampleMapper.transform(response.body()!!)
        } else {
            throw BaseApiException(response.message(), HttpException(response))
        }
    }
}