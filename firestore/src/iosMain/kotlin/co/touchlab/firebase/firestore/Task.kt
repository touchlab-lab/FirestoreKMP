package co.touchlab.firebase.firestore

import platform.Foundation.NSError

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

actual class TaskData<T:Any> {
    lateinit var ref: T
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