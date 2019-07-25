package co.touchlab.firebase.firestore

expect class TaskVoid{
    fun addListeners(successListener: ()->Unit, failureListener: (Exception)->Unit = {})
}

expect class TaskData<T>{
    fun addListeners(successListener: (T)->Unit, failureListener: (Exception)->Unit)
}