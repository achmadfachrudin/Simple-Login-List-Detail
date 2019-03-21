package com.fachrudin.project.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fachrudin.project.core.base.BaseViewModel
import com.fachrudin.project.core.common.AndroidContext.Companion.UI
import com.fachrudin.project.module.biz.entities.main.UserList
import com.fachrudin.project.module.biz.interactors.main.GetUserListInteractor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author achmad.fachrudin
 * @date 21-Mar-19
 */
class UserListViewModel @Inject constructor(private val getUserListInteractor: GetUserListInteractor) : BaseViewModel() {
    private var error: MutableLiveData<Exception>? = null
    private var userList: MutableLiveData<UserList>? = null

    fun getError(): LiveData<Exception> {
        if (error == null)
            error = MutableLiveData()
        return error as LiveData<Exception>
    }

    fun getUserList(): LiveData<UserList> {
        if (userList == null)
            userList = MutableLiveData()
        return userList as LiveData<UserList>
    }

    fun getUserListFromApi() {
        GlobalScope.launch(UI) {
            try {
                val result = getUserListInteractor.executeAsync(GetUserListInteractor.Params(1))
                userList?.value = result
            } catch (e: Exception) {
                error?.value = e
            }
        }
    }
}