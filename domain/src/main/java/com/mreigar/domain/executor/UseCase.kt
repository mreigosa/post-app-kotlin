package com.mreigar.domain.executor

abstract class UseCase<P : Any, T> {

    protected lateinit var useCaseParams: P

    abstract suspend fun run(): Result<T>

    infix fun withParams(useCaseParams: P) = also {
        this.useCaseParams = useCaseParams
    }
}