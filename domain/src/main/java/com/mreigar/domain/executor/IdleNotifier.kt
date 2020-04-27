package com.mreigar.domain.executor

interface IdleNotifier {
    fun increment(): Int

    @Throws(IllegalArgumentException::class)
    fun decrement()
}