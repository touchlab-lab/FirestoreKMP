package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRFieldPath

actual typealias FieldPath = FIRFieldPath

actual fun fieldPathOf(vararg fieldNames: String): FieldPath =
    FIRFieldPath(fieldNames.toList())