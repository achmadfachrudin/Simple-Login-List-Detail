package com.fachrudin.project.core.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fachrudin.project.BR
import com.fachrudin.project.core.annotations.ViewLayout
import com.fachrudin.project.core.bus.EventBus
import com.fachrudin.project.core.bus.EventSubscriber
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.core.owner.ViewModelOwner
import com.fachrudin.project.core.view.BindingView

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity !is AppCompatActivity) {
            throw IllegalStateException("Host activity must extends from ${AppCompatActivity::class.java.simpleName}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return getLayoutIfDefined(inflater, container)
    }

    private fun getLayoutIfDefined(inflater: LayoutInflater, container: ViewGroup?): View? {
        val layoutResId = getViewLayoutResId()
        if (layoutResId == View.NO_ID) return null

        if (this is ViewDataBindingOwner<*>) {
            val view = inflateContentViewBinding(inflater, container, layoutResId)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, this.viewModel)
            }
            if (this is BindingView) {
                binding.setVariable(BR.view, this)
            }
            return view
        } else {
            return inflater.inflate(layoutResId, container, false)
        }
    }

    protected fun subscribeEvent(observer: EventSubscriber<out Any>, classOfT: Class<out Any>) {
        EventBus.subscribe(this, observer, classOfT)
    }

    open protected fun getViewLayoutResId(): Int {
        val layout = javaClass.annotations.find { it is ViewLayout } as? ViewLayout
        return layout?.value ?: View.NO_ID
    }
}
