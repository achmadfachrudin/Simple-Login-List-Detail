package com.fachrudin.project.core.di.components

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Provider

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface AppComponent {

    fun context(): Context
    fun prefs(): SharedPreferences
}
