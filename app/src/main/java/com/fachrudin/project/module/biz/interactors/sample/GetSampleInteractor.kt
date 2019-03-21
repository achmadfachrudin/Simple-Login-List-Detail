package com.fachrudin.project.module.biz.interactors.sample

import com.fachrudin.project.module.biz.entities.sample.SampleTest
import com.fachrudin.project.module.biz.repositories.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class GetSampleInteractor(val repository: SampleRepository) {

    fun execute(): SampleTest {
        return repository.sampleTest()
    }

    suspend fun executeAsync(): SampleTest {
        return GlobalScope.async(Dispatchers.Default) { execute() }.await()
    }
}