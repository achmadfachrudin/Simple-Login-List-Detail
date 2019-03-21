package com.fachrudin.project.module.base.events

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class CrashlyticsLogEvent(val priority: Int?, val tag: String?, val message: String) {
    constructor(message: String) : this(null, null, message)
}