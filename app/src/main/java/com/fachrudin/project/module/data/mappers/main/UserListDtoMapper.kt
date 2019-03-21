package com.fachrudin.project.module.data.mappers.main

import com.fachrudin.project.core.common.protocol.Mapper
import com.fachrudin.project.module.biz.entities.main.UserList
import com.fachrudin.project.module.data.datasource.webapi.dto.main.UserListDto

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class UserListDtoMapper : Mapper<UserListDto, UserList> {
    override fun transform(from: UserListDto): UserList {
        val result = UserList()

        from.page?.let { result.page = it }
        from.perPage?.let { result.perPage = it }
        from.total?.let { result.total = it }
        from.totalPages?.let { result.totalPages = it }
        from.userItem?.let {
            val itemMapper = UserItemDtoMapper()
            result.userItem = itemMapper.transform(it)
        }

        return result
    }
}