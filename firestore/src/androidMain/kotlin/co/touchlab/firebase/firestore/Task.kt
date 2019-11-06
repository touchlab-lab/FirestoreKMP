package co.touchlab.firebase.firestore

import com.google.android.gms.tasks.Task

actual class TaskVoid(private val task:Task<Void>) {
    actual fun addListeners(successListener: () -> Unit, failureListener: (Exception) -> Unit) {
        task.addOnSuccessListener { successListener() }
        task.addOnFailureListener { failureListener(it) }
    }
}

actual class TaskData<T>(private val task:Task<T>){
    actual fun addListeners(successListener: (T) -> Unit, failureListener: (java.lang.Exception) -> Unit) {
        task.addOnSuccessListener { successListener(it) }
        task.addOnFailureListener { failureListener(it) }
    }
}
