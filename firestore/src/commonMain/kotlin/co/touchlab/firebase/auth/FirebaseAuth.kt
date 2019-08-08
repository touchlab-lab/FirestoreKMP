package co.touchlab.firebase.auth

expect class FirebaseAuth

expect class ListenerHandle

expect fun FirebaseAuth.addAuthStateListener(onAuthStateChanged: (FirebaseAuth) -> Unit): ListenerHandle
expect fun FirebaseAuth.removeAuthStateListener(listener: ListenerHandle)
expect fun FirebaseAuth.addIdTokenListener(onIdTokenChanged: (FirebaseAuth) -> Unit): ListenerHandle
expect fun FirebaseAuth.removeIdTokenListener(listener: ListenerHandle)



