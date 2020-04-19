package instrumentation.domain

import com.mreigar.domain.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestContextProvider : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Unconfined
    override val background: CoroutineDispatcher = Dispatchers.Unconfined
}