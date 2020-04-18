package com.mreigar.domain

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val background: CoroutineDispatcher
}