package com.fachrudin.project.app.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.fachrudin.project.R
import com.fachrudin.project.app.internal.di.DaggerMainFeatureComponent
import com.fachrudin.project.app.internal.di.MainFeatureComponent
import com.fachrudin.project.app.presentation.view.ProfileView
import com.fachrudin.project.app.presentation.viewmodel.ProfileViewModel
import com.fachrudin.project.app.utils.PrefManager
import com.fachrudin.project.core.base.BaseFeatureFragment
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.core.owner.ViewModelOwner
import com.fachrudin.project.databinding.FragmentProfileBinding
import com.fachrudin.project.module.base.di.components.MainRepositoryComponent
import javax.inject.Inject
import android.text.method.PasswordTransformationMethod

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
class ProfileFragment : BaseFeatureFragment(),
    ProfileView,
    ViewModelOwner<ProfileViewModel>,
    ViewDataBindingOwner<FragmentProfileBinding> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var binding: FragmentProfileBinding
    override val viewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.fragment_profile
    }

    override fun injectComponent() {
        componentAs(MainFeatureComponent::class.java).inject(this)
    }

    override fun buildComponent(): FeatureComponent {
        return DaggerMainFeatureComponent.builder()
            .appComponent(appComponent)
            .mainRepositoryComponent(getProvidedComponent(MainRepositoryComponent::class.java))
            .build()
    }

    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.profile_title)

        setUI()
    }

    private fun setUI() {
        viewModel.bTextEmail.set(PrefManager.userEmail)
        viewModel.bTextPassword.set(PrefManager.userPassword)
        binding.txtPassword.transformationMethod = PasswordTransformationMethod()
    }

    override fun onClickLogout(view: View) {
        PrefManager.isLogin = false
        activity?.finish()
        LoginActivity.startThisActivity(context!!)
    }
}