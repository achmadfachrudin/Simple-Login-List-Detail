package com.fachrudin.project.core.di

import javax.inject.Scope

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 *
 * ViewModelScope
 * Injection scope per application instance (Singleton)
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
