package com.fachrudin.project.sample.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fachrudin.project.core.base.BaseViewModel
import com.fachrudin.project.core.common.AndroidContext.Companion.UI
import com.fachrudin.project.module.biz.entities.sample.SampleTest
import com.fachrudin.project.module.biz.interactors.sample.GetSampleInteractor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class SampleViewModel @Inject constructor(val getSampleInteractor: GetSampleInteractor) : BaseViewModel() {
    var bTextTitle = ObservableField<String>()
    var bTextBody = ObservableField<String>()
    var bTextButton = ObservableField<String>("Test")

    private var error: MutableLiveData<Exception>? = null
    private var sampleTest: MutableLiveData<SampleTest>? = null

    fun getSample(): LiveData<SampleTest> {
        if (sampleTest == null)
            sampleTest = MutableLiveData()
        return sampleTest as LiveData<SampleTest>
    }

    fun getSampleFromApi() {
        GlobalScope.launch(UI) {
            try {
                val result = getSampleInteractor.executeAsync()
                sampleTest?.value = result
            } catch (e: Exception) {
                error?.value = e
            }
        }
    }
}