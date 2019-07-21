package co.touchlab.firebase.firestore

actual typealias Timestamp = com.google.firebase.Timestamp

actual fun timestampNow(): Timestamp = com.google.firebase.Timestamp.now()