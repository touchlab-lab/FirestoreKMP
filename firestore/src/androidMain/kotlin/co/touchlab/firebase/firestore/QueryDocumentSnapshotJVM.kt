package co.touchlab.firebase.firestore

actual typealias QueryDocumentSnapshot = com.google.firebase.firestore.QueryDocumentSnapshot

actual fun QueryDocumentSnapshot.data_(serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?): Map<String, Any?>? =
    if (serverTimestampBehavior == null) {
        getData()
    } else {
        getData(serverTimestampBehavior.toJvm())
    }