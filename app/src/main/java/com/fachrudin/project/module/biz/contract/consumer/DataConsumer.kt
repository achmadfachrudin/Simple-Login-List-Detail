package com.fachrudin.project.module.biz.contract.consumer

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface DataConsumer<T> {

    fun onDataAvailable(entity: T)
}