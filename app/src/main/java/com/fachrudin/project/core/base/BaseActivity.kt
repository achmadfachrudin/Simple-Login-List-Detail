package com.fachrudin.project.core.base

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
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
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setLayoutIfDefined()
    }

    private fun setLayoutIfDefined() {
        val layoutResId = getViewLayoutResId()
        if (layoutResId == View.NO_ID) return

        if (this is ViewDataBindingOwner<*>) {
            setContentViewBinding(this, layoutResId)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, this.viewModel)
            }
            if (this is BindingView) {
                binding.setVariable(BR.view, this)
            }
        } else {
            setContentView(layoutResId)
        }
    }

    protected fun subscribeEvent(observer: EventSubscriber<out Any>, classOfT: Class<out Any>) {
        EventBus.subscribe(this, observer, classOfT)
    }

    open protected fun getViewLayoutResId(): Int {
        val layout = javaClass.annotations.find { it is ViewLayout } as? ViewLayout
        return layout?.value ?: View.NO_ID
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId?.let {
            if (it == android.R.id.home)
                onToolBarBackButtonPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    open protected fun onToolBarBackButtonPressed() {
        finish()
    }

    protected fun setHomeAsUpIndicator(@DrawableRes resId:Int){
        supportActionBar?.setHomeAsUpIndicator(resId)
    }
}
