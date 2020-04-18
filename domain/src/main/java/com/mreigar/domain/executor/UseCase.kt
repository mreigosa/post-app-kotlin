package com.mreigar.domain.executor

import com.mreigar.domain.DispatcherProvider
import kotlinx.coroutines.*

abstract class UseCase<P : Any, T>(private val dispatcherProvider: DispatcherProvider = DispatcherProviderImpl()) {

    protected var useCaseParams: P? = null

    abstract suspend fun run(params: P?): Result<T>

    operator fun invoke(scope: CoroutineScope, callback: Callback<T>? = null) {
        scope.launch(dispatcherProvider.background) {
            try {
                val result = async { run(useCaseParams) }
                callback?.invoke(result.await())
            } catch (e: Exception) {
                Error(e)
            }
        }
    }

    infix fun withParams(useCaseParams: P) = also {
        this.useCaseParams = useCaseParams
    }
}

typealias Callback<T> = (Result<T>) -> Unit

class DispatcherProviderImpl : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val background: CoroutineDispatcher = Dispatchers.IO
}