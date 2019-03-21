package com.fachrudin.project.module.biz.repositories

import com.fachrudin.project.module.biz.entities.sample.SampleTest

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface SampleRepository {

    /**
     * Get list of [SampleTest]
     *
     * @return [List] of [SampleTest]
     * @throws [Exception]
     */
    @Throws(Exception::class)
    fun sampleTest(): SampleTest
}