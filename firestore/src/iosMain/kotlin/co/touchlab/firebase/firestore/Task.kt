package co.touchlab.firebase.firestore

import kotlinx.cinterop.StableRef
import platform.Foundation.NSError
import kotlin.native.concurrent.freeze

actual class TaskVoid {
    private var error: NSError? = null
    private var successListener: (() -> Unit)? = null
    private var failureListener: ((Exception) -> Unit)? = null
    private var state : State = State.Pending
    private enum class State {
        Pending, Success, Failure
    }

    fun success() {
        state = State.Success
        sendSuccess()
    }

    fun fail(err: NSError) {
        error = err
        state = State.Failure
        sendFailure()
    }

    private fun sendFailure(){
        failureListener?.invoke(DarwinException(error!!))
    }

    private fun sendSuccess(){
        successListener?.invoke()
    }

    actual fun addListeners(successListener: () -> Unit, failureListener: (Exception) -> Unit) {
        this.successListener = successListener
        this.failureListener = failureListener
        when(state){
            State.Pending -> Unit
            State.Failure -> {
                sendFailure()
            }
            State.Success -> {
                sendSuccess()
            }
        }
    }
}

actual class TaskData<T> {
    var ref: T
    get() {
        if(!refSet)
            throw IllegalStateException("ref not set")
        return refInternal as T
    }
    set(value) {
        refInternal = value
        refSet = true
    }

    private var refInternal:Any? = null
    private var refSet = false
    private var error: NSError? = null
    private var successListener: ((T) -> Unit)? = null
    private var failureListener: ((Exception) -> Unit)? = null
    private var state : State = State.Pending
    private enum class State {
        Pending, Success, Failure
    }

    fun success() {
        state = State.Success
        sendSuccess()
    }

    fun fail(err: NSError) {
        error = err
        state = State.Failure
        sendFailure()
    }

    private fun sendFailure(){
        failureListener?.invoke(DarwinException(error!!))
    }

    private fun sendSuccess(){
        successListener?.invoke(ref)
    }

    actual fun addListeners(successListener: (T) -> Unit, failureListener: (Exception) -> Unit) {
        this.successListener = successListener
        this.failureListener = failureListener
        when(state){
            State.Pending -> Unit
            State.Failure -> {
                sendFailure()
            }
            State.Success -> {
                sendSuccess()
            }
        }
    }
}

internal class DarwinException(internal val nsError: NSError):Exception(nsError.description())

internal fun makeVoid():Pair<TaskVoid, (NSError?) -> Unit>{
    val taskData = TaskVoid()
    val taskRef = StableRef.create(taskData)

    val completion = { err: NSError? ->
        val task = taskRef.get()
        taskRef.dispose()

        if (err == null) {
            task.success()
        } else {
            task.fail(err)
        }
    }

    return Pair(taskData, completion.freeze())
}