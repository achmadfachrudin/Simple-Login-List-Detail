package com.fachrudin.project.app.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fachrudin.project.R
import com.fachrudin.project.app.presentation.UserDetailActivity
import com.fachrudin.project.databinding.ItemUserListBinding
import com.fachrudin.project.app.presentation.view.UserListItemView
import com.fachrudin.project.app.presentation.viewmodel.UserListItemViewModel
import com.fachrudin.project.core.base.BaseRecycleViewAdapter
import com.fachrudin.project.core.base.BaseViewHolder
import com.fachrudin.project.core.base.GlideApp
import com.fachrudin.project.core.owner.ViewDataBindingOwner
import com.fachrudin.project.module.biz.entities.main.UserItem
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
class UserListItemAdapter @Inject constructor() : BaseRecycleViewAdapter<UserItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UserItem> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        return ViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<UserItem>, position: Int) {
        (holder as ViewHolder).bindData(getItem(position), position)
    }

    class ViewHolder(context: Context, view: View) :
        BaseViewHolder<UserItem>(context, view),
        UserListItemView,
        ViewDataBindingOwner<ItemUserListBinding> {

        override lateinit var binding: ItemUserListBinding
        private var viewModel: UserListItemViewModel? = null
        private var data: UserItem? = null

        init {
            binding.vm = UserListItemViewModel()
            binding.view = this
            viewModel = binding.vm
        }

        override fun bindData(data: UserItem) {
            // ignore
        }

        fun bindData(data: UserItem, position: Int) {
            this.data = data

            if (!data.firstName.isNullOrEmpty() && !data.lastName.isNullOrEmpty()) {
                viewModel?.bTextName?.set("${data.firstName} ${data.lastName}")
            }

            data.avatar?.let {
                GlideApp
                    .with(context)
                    .load(it)
                    .into(binding.imgUser)
            }
        }

        override fun onClickDetail(view: View) {
            UserDetailActivity.startThisActivity(context)
        }
    }
}