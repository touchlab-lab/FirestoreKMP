package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRTimestamp

actual typealias Timestamp = FIRTimestamp

actual fun timestampNow(): Timestamp = FIRTimestamp.timestamp()

actual val Timestamp.seconds: Long
    get() = getSeconds()

actual val Timestamp.nanoseconds: Int
    get() = getNanoseconds()