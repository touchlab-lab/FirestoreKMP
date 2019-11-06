package co.touchlab.firebase.firestore

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.test.BeforeTest
import kotlin.test.fail

abstract class BaseCoroutineTest : CoroutineScope {
    lateinit var firestore: FirebaseFirestore

    @BeforeTest
    fun setup() {
        firestore = getFirebaseInstance()
    }

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTraceTest()
        fail(throwable.message)
    }

    override val coroutineContext: CoroutineContext
        get() = platformCoroutineContext + job + exceptionHandler
}

internal expect fun Throwable.printStackTraceTest()
internal expect val platformCoroutineContext:CoroutineContext