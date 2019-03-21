package com.fachrudin.project.module.biz.contract

import com.fachrudin.project.module.biz.contract.consumer.DataConsumer
import com.fachrudin.project.module.biz.contract.consumer.ErrorConsumer

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class InteractorCallback<Entity> :
        DataConsumer<Entity>, ErrorConsumer {

    open fun onCancelled() {}
}
