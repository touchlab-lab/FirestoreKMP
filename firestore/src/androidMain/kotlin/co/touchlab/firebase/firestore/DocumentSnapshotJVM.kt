package co.touchlab.firebase.firestore

actual typealias DocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot

actual val DocumentSnapshot.documentId: String
    get() = id

actual val DocumentSnapshot.reference: DocumentReference
    get() = reference

actual val DocumentSnapshot.exists: Boolean
    get() = exists()

actual fun DocumentSnapshot.get(fieldPath: String): Any? = get(fieldPath)

actual fun DocumentSnapshot.get(fieldPath: FieldPath): Any? = get(fieldPath)

actual fun DocumentSnapshot.get(
    fieldPath: String,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior
): Any? = get(fieldPath, serverTimestampBehavior.toJvm())

actual fun DocumentSnapshot.get(
    fieldPath: FieldPath,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior
): Any? = get(fieldPath, serverTimestampBehavior.toJvm())

actual fun DocumentSnapshot.data_(): Map<String, Any?>? = getData()

actual fun DocumentSnapshot.data_(serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior): Map<String, Any?>? =
    getData(serverTimestampBehavior.toJvm())

internal fun DocumentSnapshotServerTimestampBehavior.toJvm():com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior =
    when(this){
        DocumentSnapshotServerTimestampBehavior.ESTIMATE -> com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
        DocumentSnapshotServerTimestampBehavior.NONE -> com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.NONE
        DocumentSnapshotServerTimestampBehavior.PREVIOUS -> com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.PREVIOUS
    }

actual fun DocumentSnapshot.contains(fieldPath: FieldPath): Boolean = contains(fieldPath)
actual fun DocumentSnapshot.contains(field: String): Boolean = contains(field)