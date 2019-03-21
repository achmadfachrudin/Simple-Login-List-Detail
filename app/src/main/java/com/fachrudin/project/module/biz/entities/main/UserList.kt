package com.fachrudin.project.module.biz.entities.main

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class UserList {
    var page: Int? = 0
    var perPage: Int? = 0
    var total: Int? = 0
    var totalPages: Int? = 0
    var userItem: List<UserItem>? = null
}