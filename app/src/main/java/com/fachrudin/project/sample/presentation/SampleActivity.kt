package com.fachrudin.project.sample.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.fachrudin.project.R
import com.fachrudin.project.sample.presentation.view.SampleView
import com.fachrudin.project.sample.presentation.viewmodel.SampleViewModel
import androidx.lifecycle.ViewModelProvider
import com.fachrudin.project.core.base.BaseFeatureActivity
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.core.owner.ViewModelOwner
import com.fachrudin.project.databinding.ActivitySampleBinding
import com.fachrudin.project.module.base.di.components.SampleRepositoryComponent
import com.fachrudin.project.sample.internal.di.SampleFeatureComponent
import com.fachrudin.project.sample.internal.di.DaggerSampleFeatureComponent
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class SampleActivity : BaseFeatureActivity(),
        SampleView,
        ViewModelOwner<SampleViewModel>,
        ViewDataBindingOwner<ActivitySampleBinding> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var binding: ActivitySampleBinding
    override val viewModel: SampleViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SampleViewModel::class.java)
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_sample
    }

    override fun injectComponent() {
        componentAs(SampleFeatureComponent::class.java).inject(this)
    }

    override fun buildComponent(): FeatureComponent {
        return DaggerSampleFeatureComponent.builder()
                .appComponent(appComponent)
                .sampleRepositoryComponent(getProvidedComponent(SampleRepositoryComponent::class.java))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Toolbar Title"
        viewModel.getSampleFromApi()
        observeData()
    }

    private fun observeData() {
        observeData(viewModel.getSample()) { sample ->
            sample?.let {
                viewModel.bTextTitle.set(it.title)
                viewModel.bTextBody.set(it.body)
            }
        }
    }

    override fun onClickSample(view: View) {
        viewModel.getSampleFromApi()
        Toast.makeText(this@SampleActivity, "yeay", Toast.LENGTH_SHORT).show()
    }
}