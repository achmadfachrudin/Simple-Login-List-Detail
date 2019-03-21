package com.fachrudin.project.sample.presentation.view

import android.view.View
import com.fachrudin.project.core.view.LifecycleView

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface SampleView : LifecycleView {
    fun onClickSample(view: View)
}