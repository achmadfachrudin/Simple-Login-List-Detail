package com.fachrudin.project.app.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
object PrefManager {
    private const val NAME = "AppPref"
    private const val MODE = Context.MODE_PRIVATE
    private const val EMPTY = ""
    private lateinit var preferences: SharedPreferences

    private val IS_LOGIN = Pair("IS_LOGIN", false)
    private val USER_EMAIL = Pair("USER_EMAIL", EMPTY)
    private val USER_PASSWORD = Pair("USER_PASSWORD", EMPTY)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit()
     * and apply() ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isLogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }

    var userEmail: String
        get() = preferences.getString(USER_EMAIL.first, USER_EMAIL.second)
        set(value) = preferences.edit {
            it.putString(USER_EMAIL.first, value)
        }

    var userPassword: String
        get() = preferences.getString(USER_PASSWORD.first, USER_PASSWORD.second)
        set(value) = preferences.edit {
            it.putString(USER_PASSWORD.first, value)
        }
}