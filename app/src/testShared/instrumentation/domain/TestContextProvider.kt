package instrumentation.domain

import com.mreigar.domain.DispatcherProvider
import com.mreigar.domain.executor.UseCaseInvoker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestContextProvider : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Unconfined
    override val background: CoroutineDispatcher = Dispatchers.Unconfined
}

object InvokerInstruments {
    fun givenAnInvoker() = UseCaseInvoker(TestContextProvider())
}