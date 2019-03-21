package com.fachrudin.project.app

import android.content.Intent
import com.fachrudin.project.module.base.events.Pagination
import com.fachrudin.project.module.base.events.PaginationEvent

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
open class PaginationRoute {
    companion object {
        fun executeEvent(event: PaginationEvent) {
            when (event.page) {
                Pagination.LOGIN -> {
//                    val intent = Intent(event.context, LoginActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    event.context.startActivity(intent)
                }
                Pagination.MAIN_PAGE -> {
//                    val intent = Intent(event.context, MainPageActivity::class.java)
//                    event.bundle?.let {
//                        intent.putExtras(event.bundle)
//                    }
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    event.context.startActivity(intent)
                }
                else -> {

                }
            }

        }
    }

}