package co.touchlab.firebase.firestore

actual typealias FieldPath = com.google.firebase.firestore.FieldPath

actual fun fieldPathOf(vararg fieldNames: String): FieldPath =
    com.google.firebase.firestore.FieldPath.of(*fieldNames)