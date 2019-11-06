package co.touchlab.firebase.firestore

expect class TaskVoid{
    fun addListeners(successListener: ()->Unit, failureListener: (Exception)->Unit)
}

expect class TaskData<T>{
    fun addListeners(successListener: (T)->Unit, failureListener: (Exception)->Unit)
}

fun TaskVoid.addSuccessListener(successListener: ()->Unit){
    addListeners(successListener, throwError)
}

fun <T> TaskData<T>.addSuccessListener(successListener: (T)->Unit){
    addListeners(successListener, throwError)
}

private val throwError:(Exception)->Unit = {throw it}