package com.mreigar.domain.executor

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.DispatcherProviderImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseInvoker(
    private val dispatcherProvider: DispatcherProvider = DispatcherProviderImpl(),
    private val idleNotifier: IdleNotifier? = null
) : Invoker {

    override fun <P : Any, T> execute(
        scope: CoroutineScope,
        useCase: UseCase<P, T>,
        callback: ((Result<T>) -> Unit)?
    ) {
        idleNotifier?.increment()
        scope.launch(dispatcherProvider.main) {
            try {
                val result = withContext(dispatcherProvider.background) { useCase.run() }
                callback?.invoke(result)
            } catch (e: Exception) {
                callback?.invoke(Error(e))
            } finally {
                idleNotifier?.decrement()
            }
        }
    }
}
