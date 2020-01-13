package co.touchlab.firebase.firestore

actual typealias Timestamp = com.google.firebase.Timestamp

actual fun timestampNow(): Timestamp = com.google.firebase.Timestamp.now()

actual val Timestamp.seconds: Long
    get() = getSeconds()

actual val Timestamp.nanoseconds: Int
    get() = getNanoseconds()
