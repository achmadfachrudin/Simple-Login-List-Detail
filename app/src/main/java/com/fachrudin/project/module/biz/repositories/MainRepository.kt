package com.fachrudin.project.module.biz.repositories

import com.fachrudin.project.module.biz.entities.main.UserList

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface MainRepository {

    /**
     * Get list of user
     *
     * @return [List] of [UserList]
     * @throws [Exception]
     */
    @Throws(Exception::class)
    fun userList(page: Int): UserList
}