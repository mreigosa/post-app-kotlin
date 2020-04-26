package com.mreigar.postapp.espresso

import androidx.test.espresso.IdlingResource
import com.mreigar.domain.executor.IdleNotifier
import java.util.concurrent.atomic.AtomicInteger

class CounterIdleResource(private val resourceName: String) : IdlingResource, IdleNotifier {

    private val counter = AtomicInteger(0)

    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = resourceName

    override fun isIdleNow(): Boolean = counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    override fun increment() = counter.getAndIncrement()

    @Throws(IllegalArgumentException::class)
    override fun decrement() {
        val counterValue = counter.decrementAndGet()
        if (counterValue == 0) {
            resourceCallback?.onTransitionToIdle()
        }

        if (counterValue < 0) {
            throw IllegalArgumentException("Counter has been corrupted!")
        }
    }
}