package co.touchlab.firebase.firestore

actual typealias QueryDocumentSnapshot = com.google.firebase.firestore.QueryDocumentSnapshot

actual val QueryDocumentSnapshot.data_: Map<String, Any?>
    get() = data

actual fun QueryDocumentSnapshot.data(serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior): Map<String, Any?> =
    getData(serverTimestampBehavior.toJvm())