package com.fachrudin.project.app.presentation.viewmodel

import androidx.databinding.ObservableField
import com.fachrudin.project.core.base.BaseViewModel
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
class ProfileViewModel @Inject constructor() : BaseViewModel() {
    var bTextEmail = ObservableField<String>()
    var bTextPassword = ObservableField<String>()
}