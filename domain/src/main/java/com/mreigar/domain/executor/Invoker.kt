package com.mreigar.domain.executor

import kotlinx.coroutines.CoroutineScope

interface Invoker {
    fun <P : Any, T> execute(scope: CoroutineScope, useCase: UseCase<P, T>, callback: ((Result<T>) -> Unit)? = null)
}