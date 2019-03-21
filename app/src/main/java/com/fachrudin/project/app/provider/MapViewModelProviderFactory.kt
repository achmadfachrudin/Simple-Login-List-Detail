package com.fachrudin.project.app.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class MapViewModelProviderFactory @Inject constructor(
        private val providers: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(classOfT: Class<T>): T {
        var provider: Provider<out ViewModel>? = providers.get(classOfT)
        if (provider == null) {
            for (entry in providers.entries) {
                if (classOfT.isAssignableFrom(entry.key)) {
                    provider = entry.value
                    break
                }
            }
        }
        if (provider == null) {
            throw IllegalArgumentException("Invalid View Model class : $classOfT")
        }
        try {
            return classOfT.cast(provider.get())
        } catch (e: ClassCastException) {
            throw IllegalStateException("Class $classOfT is not match with its provider", e)
        }
    }
}
