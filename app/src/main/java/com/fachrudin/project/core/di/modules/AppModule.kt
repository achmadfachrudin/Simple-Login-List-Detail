package com.fachrudin.project.core.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import java.util.*

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class AppModule(
        protected val app: Application,
        protected val language: String = "en") {

    protected val prefs: SharedPreferences
    protected val locale: Locale

    init {
        prefs = app.getSharedPreferences("default", Context.MODE_PRIVATE)
        locale = Locale(language)
    }
}
