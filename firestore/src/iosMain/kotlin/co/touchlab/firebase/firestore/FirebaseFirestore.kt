package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRFirestore

actual typealias FirebaseFirestore = FIRFirestore

actual fun FirebaseFirestore.collection(collectionPath: String): CollectionReference =
    collectionWithPath(collectionPath)

actual fun getFirebaseInstance(): FirebaseFirestore = FIRFirestore.firestore()