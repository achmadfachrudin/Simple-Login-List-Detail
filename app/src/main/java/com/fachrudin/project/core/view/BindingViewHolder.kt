package com.fachrudin.project.core.view

import android.view.View

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface BindingViewHolder<in T> : BindingView {

    fun onItemClick(view: View, item: T)
}
