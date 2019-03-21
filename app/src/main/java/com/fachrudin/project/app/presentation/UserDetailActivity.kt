package com.fachrudin.project.app.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.fachrudin.project.R
import com.fachrudin.project.app.internal.di.DaggerMainFeatureComponent
import com.fachrudin.project.app.internal.di.MainFeatureComponent
import com.fachrudin.project.app.presentation.view.UserDetailView
import com.fachrudin.project.app.presentation.viewmodel.UserDetailImageItemViewModel
import com.fachrudin.project.app.presentation.viewmodel.UserDetailViewModel
import com.fachrudin.project.core.base.BaseFeatureActivity
import com.fachrudin.project.core.di.components.FeatureComponent
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.core.owner.ViewModelOwner
import com.fachrudin.project.databinding.ActivityUserDetailBinding
import com.fachrudin.project.databinding.FragmentDetailBinding
import com.fachrudin.project.module.base.di.components.MainRepositoryComponent
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
class UserDetailActivity : BaseFeatureActivity(),
    UserDetailView,
    ViewModelOwner<UserDetailViewModel>,
    ViewDataBindingOwner<ActivityUserDetailBinding> {

    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var binding: ActivityUserDetailBinding
    override val viewModel: UserDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(UserDetailViewModel::class.java)
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_user_detail
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

    var inflater: LayoutInflater? = null
    private var images = intArrayOf(
        R.drawable.img_doc_1,
        R.drawable.img_doc_2,
        R.drawable.img_doc_3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.user_detail_title)

        inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding.viewPager.adapter = ImageAdapter()
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }

    internal inner class ImageAdapter : PagerAdapter() {

        var binding: FragmentDetailBinding? = null
        private var viewModel: UserDetailImageItemViewModel? = null

        override fun getCount(): Int {
            return images.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            inflater?.let {
                binding = DataBindingUtil.inflate(it, R.layout.fragment_detail, container, false)
                binding?.vm = UserDetailImageItemViewModel()
                viewModel = binding?.vm
            }

            binding?.imgGallery?.setImageResource(images[position])

            (container as ViewPager).addView(binding?.root, 0)
            return binding?.root!!
        }

        override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
            return arg0 === arg1 as View
        }

        override fun destroyItem(container: ViewGroup, position: Int, objects: Any) {
            (container as ViewPager).removeView(objects as View)
        }
    }
}