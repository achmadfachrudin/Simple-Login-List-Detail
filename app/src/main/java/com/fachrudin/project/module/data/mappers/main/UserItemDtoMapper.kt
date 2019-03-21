package com.fachrudin.project.module.data.mappers.main

import com.fachrudin.project.core.common.protocol.Mapper
import com.fachrudin.project.module.biz.entities.main.UserItem
import com.fachrudin.project.module.data.datasource.webapi.dto.main.UserItemDto

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class UserItemDtoMapper : Mapper<UserItemDto, UserItem> {
    override fun transform(from: UserItemDto): UserItem {
        val result = UserItem()

        from.id?.let { result.id = it }
        from.first_name?.let { result.firstName = it }
        from.last_name?.let { result.lastName = it }
        from.avatar?.let { result.avatar = it }

        return result
    }
}