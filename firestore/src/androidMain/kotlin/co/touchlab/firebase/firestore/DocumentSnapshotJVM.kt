package co.touchlab.firebase.firestore

actual typealias DocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot

actual fun DocumentSnapshot.contains(fieldPath: FieldPath): Boolean = contains(fieldPath)
actual fun DocumentSnapshot.contains(field: String): Boolean = contains(field)

actual val DocumentSnapshot.exists: Boolean
    get() = exists()

actual val DocumentSnapshot.reference: DocumentReference
    get() = reference

actual val DocumentSnapshot.id: String
    get() = id

actual fun DocumentSnapshot.get(
    fieldPath: String,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?
): Any? = if (serverTimestampBehavior == null) {
    get(fieldPath)
} else {
    get(fieldPath, serverTimestampBehavior.toJvm())
}

actual fun DocumentSnapshot.get(
    fieldPath: FieldPath,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?
): Any? = if (serverTimestampBehavior == null) {
    get(fieldPath)
} else {
    get(fieldPath, serverTimestampBehavior.toJvm())
}

actual fun DocumentSnapshot.data_(serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?): Map<String, Any?>? =
    if (serverTimestampBehavior == null) {
        getData()
    } else {
        getData(serverTimestampBehavior.toJvm())
    }

internal fun DocumentSnapshotServerTimestampBehavior.toJvm(): com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior =
    when (this) {
        DocumentSnapshotServerTimestampBehavior.ESTIMATE -> com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
        DocumentSnapshotServerTimestampBehavior.NONE -> com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.NONE
        DocumentSnapshotServerTimestampBehavior.PREVIOUS -> com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior.PREVIOUS
    }

actual fun DocumentSnapshot.getGeoPoint(field: String): GeoPoint? = getGeoPoint(field)
actual val DocumentSnapshot.metadata: SnapshotMetadata
    get() = metadata

actual fun DocumentSnapshot.getBoolean(field: String): Boolean? =
    getBoolean(field)

actual fun DocumentSnapshot.getDocumentReference(field: String): DocumentReference? =
    getDocumentReference(field)

actual fun DocumentSnapshot.getDouble(field: String): Double? =
    getDouble(field)

actual fun DocumentSnapshot.getLong(field: String): Long? =
    getLong(field)

actual fun DocumentSnapshot.getString(field: String): String? =
    getString(field)

actual fun DocumentSnapshot.getTimestamp(
    field: String,
    serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior?
): Timestamp? =
    if (serverTimestampBehavior == null) {
        getTimestamp(field)
    } else {
        getTimestamp(field, serverTimestampBehavior.toJvm())
    }