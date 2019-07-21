package co.touchlab.firebase.firestore

expect class TaskVoid{
    fun addListeners(successListener: ()->Unit, failureListener: (Exception)->Unit = {})
}

expect class TaskData<T:Any>{
    fun addListeners(successListener: (T)->Unit, failureListener: (Exception)->Unit)
}