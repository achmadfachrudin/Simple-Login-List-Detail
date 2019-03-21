package com.fachrudin.project.core.common.util

import android.util.Log
import com.fachrudin.project.core.common.protocol.Logger

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface AndroidLogger : Logger {

    override fun verbose(message: String) = Log.v(javaClass.simpleName, message)
    override fun info(message: String) = Log.i(javaClass.simpleName, message)
    override fun debug(message: String) = Log.d(javaClass.simpleName, message)
    override fun warning(message: String) = Log.w(javaClass.simpleName, message)
    override fun error(message: String) = Log.e(javaClass.simpleName, message)
    override fun error(message: String, throwable: Throwable) = Log.e(javaClass.simpleName, message, throwable)
}
