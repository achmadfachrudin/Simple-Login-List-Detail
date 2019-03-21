package com.fachrudin.project.core.owner

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface ViewDataBindingOwner<T : ViewDataBinding> {

    var binding: T

    fun setViewBinding(view: View) {
        binding = DataBindingUtil.bind(view)!!
    }

    fun setContentViewBinding(activity: Activity, layoutResId: Int) {
        binding = DataBindingUtil.setContentView<T>(activity, layoutResId)
    }

    fun inflateContentViewBinding(inflater: LayoutInflater, container: ViewGroup?,
                                  layoutResId: Int): View {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }
}
