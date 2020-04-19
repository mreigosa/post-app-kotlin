package instrumentation.presentation

abstract class BaseCallbackResult<T> {

    private var firedMethods = mutableListOf<FiredMethod<T>>()

    fun isMethodFired(methodName: T, withAssertions: (FiredMethod<T>.() -> Boolean) = { true }): Boolean {
        return firedMethods.firstOrNull {
            it.method == methodName
        }?.let {
            withAssertions(it)
        } ?: false
    }

    fun getTimesMethodIsFired(methodName: T): Int =
        firedMethods.filter { it.method == methodName }.size

    fun putMethodCall(methodName: T, params: Any? = Unit) {
        firedMethods.add(FiredMethod(methodName, params ?: Unit))
    }

    fun getParams(methodName: T): Any = firedMethods.first {
        it.method == methodName
    }.params
}

data class FiredMethod<T>(
    val method: T,
    val params: Any = Unit
)