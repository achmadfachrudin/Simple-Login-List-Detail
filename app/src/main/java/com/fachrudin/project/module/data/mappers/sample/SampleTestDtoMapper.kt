package com.fachrudin.project.module.data.mappers.sample

import com.fachrudin.project.module.biz.entities.sample.SampleTest
import com.fachrudin.project.module.data.datasource.webapi.dto.sample.SampleTestDto
import com.fachrudin.project.core.common.protocol.Mapper

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class SampleTestDtoMapper : Mapper<SampleTestDto, SampleTest> {
    override fun transform(from: SampleTestDto): SampleTest {
        val result = SampleTest()

        from.userId?.let { result.userId = it }
        from.id?.let { result.id = it }
        from.title?.let { result.title = it }
        from.body?.let { result.body = it }

        return result
    }
}