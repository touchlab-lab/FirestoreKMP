package co.touchlab.firebase.firestore

expect open class DocumentSnapshot

expect fun DocumentSnapshot.contains(fieldPath: FieldPath):Boolean
expect fun DocumentSnapshot.contains(field: String):Boolean
expect val DocumentSnapshot.exists: Boolean
expect val DocumentSnapshot.reference: DocumentReference
expect val DocumentSnapshot.id: String
expect val DocumentSnapshot.metadata: SnapshotMetadata
expect fun DocumentSnapshot.get(fieldPath: String, serverTimestampBehavior:DocumentSnapshotServerTimestampBehavior? = null):Any?
expect fun DocumentSnapshot.get(fieldPath: FieldPath, serverTimestampBehavior:DocumentSnapshotServerTimestampBehavior? = null):Any?
expect fun DocumentSnapshot.data_(serverTimestampBehavior:DocumentSnapshotServerTimestampBehavior? = null):Map<String, Any?>?
//TODO: Need iOS Blob type
expect fun DocumentSnapshot.getBoolean(field: String): Boolean?
expect fun DocumentSnapshot.getDocumentReference(field: String): DocumentReference?
expect fun DocumentSnapshot.getDouble(field: String): Double?
expect fun DocumentSnapshot.getLong(field: String): Long?
expect fun DocumentSnapshot.getString(field: String): String?
expect fun DocumentSnapshot.getTimestamp(field: String, serverTimestampBehavior: DocumentSnapshotServerTimestampBehavior? = null): Timestamp?
expect fun DocumentSnapshot.getGeoPoint(field: String): GeoPoint?


enum class DocumentSnapshotServerTimestampBehavior{
    NONE,
    ESTIMATE,
    PREVIOUS
}