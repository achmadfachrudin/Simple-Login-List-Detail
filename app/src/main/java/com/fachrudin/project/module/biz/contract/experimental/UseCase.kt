package com.fachrudin.project.module.biz.contract.experimental

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface UseCase<Params, Entity> {

    @Throws(Exception::class)
    fun execute(params: Params?): Entity
}