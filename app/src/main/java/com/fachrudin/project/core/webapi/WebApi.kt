package com.fachrudin.project.core.webapi

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class WebApi private constructor(builder: Builder) : WebApiSpec, WebApiConnection {

    override val baseUrl: String
    override val version: String
    override val connectTimeout: Long
    override val readTimeout: Long
    override val enableLogging: Boolean

    init {
        this.baseUrl = builder.baseUrl
        this.version = builder.version
        this.connectTimeout = builder.connectTimeout
        this.readTimeout = builder.readTimeout
        this.enableLogging = builder.enableLogging
    }

    class Builder(val baseUrl: String, val version: String) {

        var connectTimeout: Long = 30
            private set
            get() = field
        var readTimeout: Long = 30
            private set
            get() = field
        var enableLogging: Boolean = false
            private set
            get() = field

        fun setConnectTimeout(timeout: Long): Builder {
            this.connectTimeout = timeout
            return this
        }

        fun setReadTimeout(timeout: Long): Builder {
            this.readTimeout = timeout
            return this
        }

        fun setLoggingEnabled(enableLogging: Boolean): Builder {
            this.enableLogging = enableLogging
            return this
        }

        fun build(): WebApi {
            return WebApi(this)
        }
    }
}