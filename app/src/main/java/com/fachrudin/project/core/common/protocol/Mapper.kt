package com.fachrudin.project.core.common.protocol

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface Mapper<in From, out To> {

    fun transform(from: From): To

    fun transform(from: List<From>): List<To> {
        val result = ArrayList<To>()
        for (item in from) {
            result.add(transform(item))
        }
        return result
    }
}
