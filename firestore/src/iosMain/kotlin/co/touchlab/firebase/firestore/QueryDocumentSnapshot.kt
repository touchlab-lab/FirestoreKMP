package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRQueryDocumentSnapshot

actual typealias QueryDocumentSnapshot = FIRQueryDocumentSnapshot

actual fun QueryDocumentSnapshot.data_(serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?): Map<String, Any?>? =
    if (serverTimestampBehavior == null) {
        data()
    } else {
        dataWithServerTimestampBehavior(serverTimestampBehavior.toObjc())
    } as Map<String, Any?>?