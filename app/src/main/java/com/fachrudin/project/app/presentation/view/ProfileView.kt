package com.fachrudin.project.app.presentation.view

import android.view.View
import com.fachrudin.project.core.view.LifecycleView

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
interface ProfileView : LifecycleView {
    fun onClickLogout(view: View)
}