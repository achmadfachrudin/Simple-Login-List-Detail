package com.fachrudin.project.app.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fachrudin.project.R
import com.fachrudin.project.app.internal.di.DaggerMainFeatureComponent
import com.fachrudin.project.app.internal.di.MainFeatureComponent
import com.fachrudin.project.app.presentation.view.UserListView
import com.fachrudin.project.app.presentation.view.adapter.UserListItemAdapter
import com.fachrudin.project.app.presentation.viewmodel.UserListViewModel
import com.fachrudin.project.core.base.BaseFeatureActivity
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.core.owner.ViewModelOwner
import com.fachrudin.project.databinding.ActivityMainBinding
import com.fachrudin.project.module.base.di.components.MainRepositoryComponent
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
class MainActivity : BaseFeatureActivity(),
    UserListView,
    ViewModelOwner<UserListViewModel>,
    ViewDataBindingOwner<ActivityMainBinding> {

    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }

    @Inject
    override lateinit var listAdapter: UserListItemAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var binding: ActivityMainBinding
    override val viewModel: UserListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_main
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
        get() = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        set(value) {}

    private var firstOpen = true
    private var time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.user_list_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.navBottom.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_user -> {
                    if (firstOpen || (R.id.menu_user != getSelectedItem(binding.navBottom))) {
                        firstOpen = false
                        changeMainFragmentContent(UserListFragment())
                    }
                }
                R.id.menu_profile -> {
                    if (R.id.menu_profile != getSelectedItem(binding.navBottom)) {
                        changeMainFragmentContent(ProfileFragment())
                    }
                }
            }
            true
        }
        binding.navBottom.selectedItemId = R.id.menu_user
    }

    private fun changeMainFragmentContent(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_content, fragment)
            .commit()
    }

    private fun getSelectedItem(bottomNavigationView: BottomNavigationView): Int {
        val menu = bottomNavigationView.menu
        for (i in 0 until bottomNavigationView.menu.size()) {
            val menuItem = menu.getItem(i)
            if (menuItem.isChecked) {
                return menuItem.itemId
            }
        }
        return 0
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