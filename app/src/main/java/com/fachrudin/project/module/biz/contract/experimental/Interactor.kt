package com.fachrudin.project.module.biz.contract.experimental

import com.fachrudin.project.module.biz.contract.InteractorCallback
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class Interactor<Params, Entity> : UseCase<Params, Entity> {

    fun executeAsync(
            context: CoroutineContext,
            params: Params?,
            callback: InteractorCallback<Entity>?): Job = GlobalScope.launch(context) {
        try {
            val data = executeAsync(params)
            callback?.onDataAvailable(data)
        } catch (e: CancellationException) {
            callback?.onCancelled()
        } catch (e: Exception) {
            callback?.onError(e)
        }
    }

    suspend fun executeAsync(params: Params?): Entity {
        return GlobalScope.async(Dispatchers.Default) { execute(params) }.await()
    }
}