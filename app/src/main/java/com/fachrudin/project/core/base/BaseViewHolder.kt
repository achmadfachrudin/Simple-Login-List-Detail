package com.fachrudin.project.core.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.fachrudin.project.BR
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.core.owner.ViewModelOwner
import com.fachrudin.project.core.view.BindingViewHolder

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class BaseViewHolder<T>(val context: Context, view: View) : RecyclerView.ViewHolder(view) {

    init {
        if (this is ViewDataBindingOwner<*>) {
            setViewBinding(view)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, viewModel)
                binding.executePendingBindings();
            }
            if (this is BindingViewHolder<*>) {
                binding.setVariable(BR.view, this)
            }
        }
    }

    abstract fun bindData(data: T)
}
