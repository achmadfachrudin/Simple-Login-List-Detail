package com.fachrudin.project.core.config.local

import android.content.Context
import android.content.SharedPreferences
import com.fachrudin.project.core.config.ConfigChangeListener
import com.fachrudin.project.core.config.ConfigProperties
import com.fachrudin.project.core.config.MutableConfig

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class SharedPreferencesConfig(context: Context, name: String) : MutableConfig {

    companion object Defaults {
        const val STRING = ""
        const val BOOLEAN = false
        const val INT = -1
        const val LONG = INT.toLong()
        const val FLOAT = INT.toFloat()
    }

    private val storage: SharedPreferences
    private var onConfigChangeListener: ConfigChangeListener? = null

    init {
        storage = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun setConfigChangeListener(listener: ConfigChangeListener) {
        this.onConfigChangeListener = listener
    }

    override fun <T> set(prop: ConfigProperties<T>, value: T) {
        val edit = storage.edit()
        when (prop.type) {
            String::class.java -> edit.putString(prop.name, String::class.java.cast(value))
            Boolean::class.java -> edit.putBoolean(prop.name, Boolean::class.java.cast(value)!!)
            Int::class.java -> edit.putInt(prop.name, Int::class.java.cast(value)!!)
            Long::class.java -> edit.putLong(prop.name, Long::class.java.cast(value)!!)
            Float::class.java -> edit.putFloat(prop.name, Float::class.java.cast(value)!!)
            else -> throw IllegalArgumentException("Invalid config properties type")
        }
        val oldValue = get(prop)
        edit.apply()
        onConfigChangeListener?.onChange(prop, oldValue, value)
    }

    override fun <T> get(prop: ConfigProperties<T>): T {
        val value: Any = when (prop.type) {
            String::class.java -> storage.getString(prop.name, STRING)
            Boolean::class.java -> storage.getBoolean(prop.name, BOOLEAN)
            Int::class.java -> storage.getInt(prop.name, INT)
            Long::class.java -> storage.getLong(prop.name, LONG)
            Float::class.java -> storage.getFloat(prop.name, FLOAT)
            else -> throw IllegalArgumentException("Invalid config properties type")
        }
        return prop.type.cast(value)!!
    }
}
