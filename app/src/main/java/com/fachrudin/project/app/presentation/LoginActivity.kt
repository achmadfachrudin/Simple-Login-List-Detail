package com.fachrudin.project.app.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProviders
import com.fachrudin.project.R
import androidx.lifecycle.ViewModelProvider
import com.fachrudin.project.app.internal.di.DaggerMainFeatureComponent
import com.fachrudin.project.app.internal.di.MainFeatureComponent
import com.fachrudin.project.app.presentation.view.LoginView
import com.fachrudin.project.app.presentation.viewmodel.LoginViewModel
import com.fachrudin.project.app.utils.PrefManager
import com.fachrudin.project.core.base.BaseFeatureActivity
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.core.owner.ViewModelOwner
import com.fachrudin.project.databinding.ActivityLoginBinding
import com.fachrudin.project.module.base.di.components.MainRepositoryComponent
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
class LoginActivity : BaseFeatureActivity(),
    LoginView,
    ViewModelOwner<LoginViewModel>,
    ViewDataBindingOwner<ActivityLoginBinding> {

    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var binding: ActivityLoginBinding
    override val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_login
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

    private var time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.login_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val isLogin = PrefManager.isLogin

        if (isLogin) {
            finish()
            MainActivity.startThisActivity(this)
        }
    }

    override fun onClickLogin(view: View) {
        validateForm()
    }

    private fun validateForm() {
        var isValid = true

        viewModel.bTextEmailError.set(null)
        if (viewModel.bTextEmail.get().isNullOrBlank()) {
            isValid = false
            viewModel.bTextEmailError.set(getString(R.string.login_error_email_empty))
        } else if (!Patterns.EMAIL_ADDRESS.matcher(viewModel.bTextEmail.get()).matches()) {
            isValid = false
            viewModel.bTextEmailError.set(getString(R.string.login_error_email_not_valid))
        }

        viewModel.bTextPasswordError.set(null)
        if (viewModel.bTextPassword.get().isNullOrBlank()) {
            isValid = false
            viewModel.bTextPasswordError.set(getString(R.string.login_error_password_empty))
        }

        if (isValid) {
            PrefManager.userEmail = viewModel.bTextEmail.get()!!
            PrefManager.userPassword = viewModel.bTextPassword.get()!!
            PrefManager.isLogin = true

            finish()
            MainActivity.startThisActivity(this)
        }
    }

    override fun onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, getString(R.string.app_msg_close), Toast.LENGTH_SHORT).show()
        }
        time = System.currentTimeMillis()
    }
}