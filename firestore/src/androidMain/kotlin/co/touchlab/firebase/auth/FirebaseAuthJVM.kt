package co.touchlab.firebase.auth

actual typealias FirebaseAuth = com.google.firebase.auth.FirebaseAuth

actual class ListenerHandle(val listen:Any)

actual fun FirebaseAuth.removeAuthStateListener(listener: ListenerHandle) {
    removeAuthStateListener(listener.listen as com.google.firebase.auth.FirebaseAuth.AuthStateListener)
}

actual fun FirebaseAuth.removeIdTokenListener(listener: ListenerHandle) {
    removeIdTokenListener(listener.listen as com.google.firebase.auth.FirebaseAuth.IdTokenListener)
}

actual fun FirebaseAuth.addAuthStateListener(onAuthStateChanged: (FirebaseAuth) -> Unit): ListenerHandle {
    val value = com.google.firebase.auth.FirebaseAuth.AuthStateListener {
        onAuthStateChanged(it)
    }
    addAuthStateListener(value)

    return ListenerHandle(value)
}

actual fun FirebaseAuth.addIdTokenListener(onIdTokenChanged: (FirebaseAuth) -> Unit): ListenerHandle {
    val value = com.google.firebase.auth.FirebaseAuth.IdTokenListener {
        onIdTokenChanged(it)
    }
    addIdTokenListener(value)

    return ListenerHandle(value)
}