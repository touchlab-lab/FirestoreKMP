package co.touchlab.firebase.firestore

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_current_queue
import kotlin.coroutines.CoroutineContext

internal actual fun Throwable.printStackTraceTest() {
    this.printStackTrace()
}

internal class DarwinDispatcher: CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_current_queue()) {
            block.run()
        }
    }
}

internal actual val platformCoroutineContext: CoroutineContext
    get() = DarwinDispatcher()