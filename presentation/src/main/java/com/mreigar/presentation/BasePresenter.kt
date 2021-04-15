package com.mreigar.presentation

import com.mreigar.domain.executor.Invoker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent
import java.lang.ref.WeakReference
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<out T>(
    private val view: WeakReference<T>,
    protected val invoker: Invoker
) : BasePresenterContract, CoroutineScope, KoinComponent {

    private var job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun view(): T? = view.get()

    override fun onCreate() {}

    override fun onReady() {}

    override fun onStart() {
        if (job.isCancelled) {
            job = SupervisorJob()
        }
    }

    override fun onResume() {}

    override fun onPause() {}

    override fun onStop() {}

    override fun onDestroy() {
        job.cancel()
        view.clear()
    }

    override fun onRestoreInstanceState() {}

    override fun onSaveInstanceState() {}

    override fun onActivityResult() {}
}
