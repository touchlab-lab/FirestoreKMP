package co.touchlab.firebase.firestore

expect fun configTestFirestore()

expect fun <T> runBlockingTest(block: suspend () -> T)

expect val isMainThread: Boolean