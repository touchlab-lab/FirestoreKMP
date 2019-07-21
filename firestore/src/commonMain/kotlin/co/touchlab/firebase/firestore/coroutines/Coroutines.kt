package co.touchlab.firebase.firestore.coroutines

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal interface Callback<T> {
    fun onComplete(result: T)
    fun onError(e: Exception)
}

internal suspend fun <T> awaitCallback(block: (Callback<T>) -> Unit): T =
    suspendCancellableCoroutine { cont ->
        block(object : Callback<T> {
            override fun onComplete(result: T) = cont.resume(result)
            override fun onError(e: Exception) = cont.resumeWithException(e)
        })
    }