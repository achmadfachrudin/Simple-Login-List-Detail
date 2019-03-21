package com.fachrudin.project.core.config

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface RemoteSourceConfig : Config {

    fun sync(force: Boolean)
}
