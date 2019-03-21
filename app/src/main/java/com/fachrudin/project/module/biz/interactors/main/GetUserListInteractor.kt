package com.fachrudin.project.module.biz.interactors.main

import com.fachrudin.project.module.biz.contract.experimental.Interactor
import com.fachrudin.project.module.biz.entities.main.UserList
import com.fachrudin.project.module.biz.repositories.MainRepository

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class GetUserListInteractor(val repository: MainRepository) :
    Interactor<GetUserListInteractor.Params, UserList>() {

    override fun execute(params: Params?): UserList {
        val page = params?.page
            ?: throw IllegalArgumentException("missing params: ${Params::page}")
        return repository.userList(page)
    }

    data class Params(val page: Int)
}