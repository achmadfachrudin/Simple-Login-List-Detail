package com.fachrudin.project.core.common

import kotlinx.coroutines.Dispatchers

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class AndroidContext {

    companion object {
        @JvmField
        val UI = Dispatchers.Main
    }
}