package com.fachrudin.project.app.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fachrudin.project.R
import com.fachrudin.project.app.internal.di.DaggerMainFeatureComponent
import com.fachrudin.project.app.internal.di.MainFeatureComponent
import com.fachrudin.project.app.presentation.view.UserListView
import com.fachrudin.project.app.presentation.view.adapter.UserListItemAdapter
import com.fachrudin.project.app.presentation.viewmodel.UserListViewModel
import com.fachrudin.project.core.base.BaseFeatureFragment
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.core.owner.ViewModelOwner
import com.fachrudin.project.databinding.FragmentUserListBinding
import com.fachrudin.project.module.base.di.components.MainRepositoryComponent
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
class UserListFragment : BaseFeatureFragment(),
    UserListView,
    ViewModelOwner<UserListViewModel>,
    ViewDataBindingOwner<FragmentUserListBinding> {

    @Inject
    override lateinit var listAdapter: UserListItemAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var binding: FragmentUserListBinding
    override val viewModel: UserListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.fragment_user_list
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

    override var layoutManager: LinearLayoutManager
        get() = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        set(value) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.user_list_title)

        viewModel.getUserListFromApi()
        observeError()
        observeData()

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.vertical_space_divider)!!)
        binding.rvUser.addItemDecoration(divider)
    }

    private fun observeError() {
        observeData(viewModel.getError()) { error ->
            error?.let {
                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeData() {
        observeData(viewModel.getUserList()) { user ->
            user?.userItem?.let {
                listAdapter.setData(it)
            }
        }
    }
}