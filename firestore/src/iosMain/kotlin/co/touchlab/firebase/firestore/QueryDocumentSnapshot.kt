package co.touchlab.firebase.firestore

import cocoapods.FirebaseFirestore.FIRQueryDocumentSnapshot

actual typealias QueryDocumentSnapshot = FIRQueryDocumentSnapshot

actual val QueryDocumentSnapshot.data_: Map<String, Any?>
    get() = data() as Map<String, Any?>

actual fun QueryDocumentSnapshot.data(serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior): Map<String, Any?> =
    dataWithServerTimestampBehavior(serverTimestampBehavior.toObjc()) as Map<String, Any?>